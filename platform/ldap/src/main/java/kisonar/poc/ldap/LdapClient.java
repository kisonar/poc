package kisonar.poc.ldap;

import kisonar.platform.domain.user.UserRecord;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static kisonar.platform.domain.BaseDefinitions.COMA;
import static kisonar.platform.domain.BaseDefinitions.EMPTY;
import static kisonar.platform.domain.BaseDefinitions.EQUALS;
import static kisonar.poc.ldap.LDAPConsts.*;

public class LdapClient {

      private final static Logger LOGGER = Logger.getLogger(LdapClient.class.getName());
      private final DirContext ctx;

      public LdapClient(DirContext ctx) {
            this.ctx = ctx;
      }

      public void createGroup(String groupName) throws NamingException {
            Attributes attrs = new BasicAttributes(true);
            attrs.put(OBJECT_CLASS, "organizationalUnit");
            String groupDn = generateGroupFQName(groupName);
            ctx.createSubcontext(groupDn, attrs);
      }

      public void removeGroup(String groupName) throws NamingException {
            ctx.destroySubcontext(generateGroupFQName(groupName));
      }

      public List<String> fetchGroups() throws NamingException {
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(SearchControls.ONELEVEL_SCOPE);
            NamingEnumeration<SearchResult> namingEnumeration =
                    ctx.search(EMPTY, OU_ALL, new Object[]{}, searchControls);
            List<String> groups = new ArrayList<>();
            while (namingEnumeration.hasMore()) {
                  SearchResult sr = namingEnumeration.next();
                  Attributes attributes = sr.getAttributes();
                  Attribute attribute = attributes.get(OU);
                  groups.add(attribute.get().toString());
            }
            return Collections.unmodifiableList(groups);
      }

      public void createUser(UserRecord userRecord, String groupName) throws NamingException {
            Attributes attributes = new BasicAttributes();
            attributes.put(PERSON);
            attributes.put(ORGANIZATIONAL_PERSON);
            attributes.put(INET_ORG_PERSON);
            // attributes.put(LDAPConsts.POSSIX_ACCOUNT);
            //attributes.put(LDAPConsts.SHADOW_ACCOUNT);
            //String valueHomeDirectory = "/home/"+prefix;
            // Attribute attributeHomeDirectory = new BasicAttribute(LDAPConsts.HOME_DIRECTORY,valueHomeDirectory);
            Attribute attributeUid = new BasicAttribute(UID, userRecord.ldapId());
            Attribute attributeCn = new BasicAttribute(CN, userRecord.name());
            Attribute attributeSn = new BasicAttribute(SN, userRecord.surname());
            Attribute attributePassword = new BasicAttribute(PASSWORD, userRecord.password());
            //Attribute attributeEmail = new BasicAttribute(EMAIL, user.email);
            attributes.put(attributeUid);
            attributes.put(attributeCn);
            attributes.put(attributeSn);
            attributes.put(attributePassword);
            //attributes.put(attributeEmail);

            String dn = generateUserFQName(userRecord.ldapId(), List.of(groupName));
            ctx.createSubcontext(dn, attributes);
            LOGGER.log(Level.INFO, String.format("Added user with ID: %s to group %s", userRecord.ldapId(), groupName));
            LOGGER.log(Level.INFO, String.format("FQDN is: %s", dn));
      }

      public List<UserRecord> fetchUsers() throws NamingException {
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration<SearchResult> namingEnumeration =
                    ctx.search("", UID_ALL, new Object[]{}, searchControls);
            List<UserRecord> userRecords = new ArrayList<>();
            while (namingEnumeration.hasMore()) {
                  SearchResult sr = namingEnumeration.next();
                  Attributes attributes = sr.getAttributes();
                  userRecords.add(extractUser(attributes));
            }
            return Collections.unmodifiableList(userRecords);
      }

      //TODO
      public Optional<UserRecord> findUser(String userId) throws NamingException {
            List<String> groups = fetchGroups();
            String groupsBase = generateGroupsString(groups);
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String filter = "(&(objectclass=" + INETORGPERSON + ")(" + UID + EQUALS + userId + "))";
            NamingEnumeration<SearchResult> results = ctx.search(groupsBase, filter, searchControls);
            UserRecord userRecord = null;
            while (results.hasMore()) {
                  SearchResult sr = results.next();
                  Attributes attributes = sr.getAttributes();
                  Attribute attributeUid = attributes.get(UID);
                  if (attributeUid != null) {
                        String candidateUserId = attributeUid.get().toString();
                        if (candidateUserId.equals(userId)) {
                              userRecord = extractUser(attributes);
                        }
                  }
            }
            return Optional.ofNullable(userRecord);
      }

      public void close() throws NamingException {
            ctx.close();
      }

      public void removeUser(String userId) throws NamingException {
            Optional<String> userFQNameOptional = findUserFQName(userId);
            if (userFQNameOptional.isPresent())
                  ctx.destroySubcontext(userFQNameOptional.get());
            LOGGER.log(Level.INFO, String.format("Removed user with ID: %s", userId));
      }

      private Optional<String> findUserFQName(String userId) throws NamingException {
            Optional<String> userDn = Optional.empty();
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String filter = "(&(" + OBJECT_CLASS + EQUALS + INETORGPERSON + ")(" + UID + EQUALS + userId + "))";
            NamingEnumeration<SearchResult> results = ctx.search(EMPTY, filter, searchControls);
            while (results.hasMore()) {
                  SearchResult searchResult = results.next();
                  userDn = Optional.of(searchResult.getName());
                  break;
            }

            return userDn;
      }

      private String generateUserFQName(String uid, List<String> groups) {
            return UID + EQUALS + uid +
                    COMA +
                    generateGroupsString(groups);
      }

      private String generateGroupFQName(String groupName) {
            return OU + EQUALS + groupName;
      }

      private String generateGroupsString(List<String> groups) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String group : groups) {
                  stringBuilder.append(OU).append(EQUALS).append(group).append(COMA);
            }
            if (!groups.isEmpty())
                  stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
      }

      private UserRecord extractUser(Attributes attributes) throws NamingException {
            String nameCn = attributes.get(CN).toString();
            String surnameSN = attributes.get(SN).toString();
            String password = new String((byte[]) attributes.get(PASSWORD).get());
            return new UserRecord(attributes.get(UID).toString(), nameCn, surnameSN, password, nameCn + "." + surnameSN + "@email.com");
      }

}