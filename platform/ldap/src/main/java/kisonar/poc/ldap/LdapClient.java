package kisonar.poc.ldap;

import static kisonar.poc.ldap.LDAPConsts.*;
import static kisonar.platform.domain.BaseDefinitions.*;

import kisonar.platform.domain.User;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LdapClient {

    private final static Logger LOGGER = Logger.getLogger(LdapClient.class.getName());
    private DirContext ctx;

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

    public void createUser(User user, String groupName) throws NamingException {
        Attributes attributes = new BasicAttributes();
        attributes.put(PERSON);
        attributes.put(ORGANIZATIONAL_PERSON);
        attributes.put(INET_ORG_PERSON);
        // attributes.put(LDAPConsts.POSSIX_ACCOUNT);
        //attributes.put(LDAPConsts.SHADOW_ACCOUNT);
        //String valueHomeDirectory = "/home/"+prefix;
        // Attribute attributeHomeDirectory = new BasicAttribute(LDAPConsts.HOME_DIRECTORY,valueHomeDirectory);
        Attribute attributeUid = new BasicAttribute(UID, user.userId);
        Attribute attributeCn = new BasicAttribute(CN, user.name);
        Attribute attributeSn = new BasicAttribute(SN, user.surname);
        Attribute attributePassword = new BasicAttribute(PASSWORD, user.password);
        //Attribute attributeEmail = new BasicAttribute(EMAIL, user.email);
        attributes.put(attributeUid);
        attributes.put(attributeCn);
        attributes.put(attributeSn);
        attributes.put(attributePassword);
        //attributes.put(attributeEmail);

        String dn = generateUserFQName(user.userId, List.of(groupName));
        ctx.createSubcontext(dn, attributes);
        LOGGER.log(Level.INFO, String.format("Added user with ID: %s to group %s", user.userId, groupName));
        LOGGER.log(Level.INFO, String.format("FQDN is: %s", dn));
    }

    public List<User> fetchUsers() throws NamingException {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> namingEnumeration =
                ctx.search("", UID_ALL, new Object[]{}, searchControls);
        List<User> users = new ArrayList<>();
        while (namingEnumeration.hasMore()) {
            SearchResult sr = namingEnumeration.next();
            Attributes attributes = sr.getAttributes();
            users.add(extractUser(attributes));
        }
        return Collections.unmodifiableList(users);
    }

    //TODO
    public Optional<User> findUser(String userId) throws NamingException {
        List<String> groups = fetchGroups();
        String groupsBase = generateGroupsString(groups);
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String filter = "(&(objectclass=" + INETORGPERSON + ")(" + UID + EQUALS + userId + "))";
        NamingEnumeration<SearchResult> results = ctx.search(groupsBase, filter, searchControls);
        User user = null;
        while (results.hasMore()) {
            SearchResult sr = results.next();
            Attributes attributes = sr.getAttributes();
            Attribute attributeUid = attributes.get(UID);
            if (attributeUid != null) {
                String candidateUserId = attributeUid.get().toString();
                if (candidateUserId.equals(userId)) {
                    user = extractUser(attributes);
                }
            }
        }
        return Optional.ofNullable(user);
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
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(UID).append(EQUALS).append(uid)
                .append(COMA)
                .append(generateGroupsString(groups))
                .toString();
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

    private User extractUser(Attributes attributes) throws NamingException {
        String nameCn = attributes.get(CN).toString();
        String surnameSN = attributes.get(SN).toString();
        String password = new String((byte[]) attributes.get(PASSWORD).get());
        User user = new User(attributes.get(UID).toString(), nameCn, surnameSN, password);
        return user;
    }

}