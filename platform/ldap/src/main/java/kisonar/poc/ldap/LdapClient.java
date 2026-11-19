package kisonar.poc.ldap;

import kisonar.platform.domain.user.User;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Rdn;
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
    // {0} is substituted and escaped by JNDI, so user input cannot alter the filter
    private static final String USER_BY_UID_FILTER =
            "(&(%s=%s)(%s={0}))".formatted(OBJECT_CLASS, INETORGPERSON, UID);
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

    public void createUser(User user, String groupName) throws NamingException {
        var attributes = new BasicAttributes();
        attributes.put(PERSON);
        attributes.put(ORGANIZATIONAL_PERSON);
        attributes.put(INET_ORG_PERSON);
        // attributes.put(LDAPConsts.POSSIX_ACCOUNT);
        //attributes.put(LDAPConsts.SHADOW_ACCOUNT);
        //String valueHomeDirectory = "/home/"+prefix;
        // Attribute attributeHomeDirectory = new BasicAttribute(LDAPConsts.HOME_DIRECTORY,valueHomeDirectory);
        var attributeUid = new BasicAttribute(UID, user.ldapId());
        var attributeCn = new BasicAttribute(CN, user.name());
        var attributeSn = new BasicAttribute(SN, user.surname());
        var attributePassword = new BasicAttribute(PASSWORD, user.password());
        Attribute attributeEmail = new BasicAttribute(EMAIL, user.email());
        attributes.put(attributeUid);
        attributes.put(attributeCn);
        attributes.put(attributeSn);
        attributes.put(attributePassword);
        attributes.put(attributeEmail);

        var dn = generateUserFQName(user.ldapId(), List.of(groupName));
        ctx.createSubcontext(dn, attributes);
        LOGGER.log(Level.INFO, "Added user with ID: %s to group %s".formatted(user.ldapId(), groupName));
        LOGGER.log(Level.INFO, "FQDN is: %s".formatted(dn));
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

    public Optional<User> findUser(String userId) throws NamingException {
        List<String> groups = fetchGroups();
        String groupsBase = generateGroupsString(groups);
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> results =
                ctx.search(groupsBase, USER_BY_UID_FILTER, new Object[]{userId}, searchControls);
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
        var userFQNameOptional = findUserFQName(userId);
        if (userFQNameOptional.isPresent())
            ctx.destroySubcontext(userFQNameOptional.get());
        LOGGER.log(Level.INFO, "Removed user with ID: %s".formatted(userId));
    }

    private Optional<String> findUserFQName(String userId) throws NamingException {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> results =
                ctx.search(EMPTY, USER_BY_UID_FILTER, new Object[]{userId}, searchControls);
        return (results.hasMore() ? Optional.of(results.next().getName()) : Optional.empty());
    }

    private String generateUserFQName(String uid, List<String> groups) {
        return UID + EQUALS + Rdn.escapeValue(uid) +
                COMA +
                generateGroupsString(groups);
    }

    private String generateGroupFQName(String groupName) {
        return OU + EQUALS + Rdn.escapeValue(groupName);
    }

    private String generateGroupsString(List<String> groups) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String group : groups) {
            stringBuilder.append(OU).append(EQUALS).append(Rdn.escapeValue(group)).append(COMA);
        }
        return (!groups.isEmpty()) ?
                stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString() : stringBuilder.toString();
    }

    private User extractUser(Attributes attributes) throws NamingException {
        String nameCn = attributes.get(CN).toString();
        String surnameSN = attributes.get(SN).toString();
        String password = new String((byte[]) attributes.get(PASSWORD).get());
        return new User(attributes.get(UID).toString(), nameCn, surnameSN, password, nameCn + "." + surnameSN + "@email.com");
    }
}