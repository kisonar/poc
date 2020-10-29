package kisonar.poc.ldap;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LdapClient {

    private final static Logger LOGGER = Logger.getLogger(LdapClient.class.getName());
    private DirContext ctx;

    public LdapClient(DirContext ctx) {
        this.ctx = ctx;
    }

    public void listUsers() throws NamingException {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> namingEnumeration =
                ctx.search("", LDAPConsts.UID_ALL, new Object[]{}, searchControls);
        LOGGER.log(Level.INFO, "---- Users-----");
        while (namingEnumeration.hasMore()) {
            SearchResult sr = namingEnumeration.next();

            LOGGER.log(Level.INFO, "SearchResult: " + sr);
            // LOGGER.log(Level.INFO, "Name in namespace :" + sr.getNameInNamespace());
            //LOGGER.log(Level.INFO, "DN: " + sr.getName());
            //LOGGER.log(Level.INFO, "UID: " + sr.getAttributes().get("uid"));
            //LOGGER.log(Level.INFO, "dc: " + sr.getAttributes().get("dc"));
            //LOGGER.log(Level.INFO, "Password encoded by bytes: " + sr.getAttributes().get("userPassword").get());
            // LOGGER.log(Level.INFO, "Password:" + new String((byte[]) sr.getAttributes().get("userPassword").get()));
        }
    }

    public void listGroups() throws NamingException {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.ONELEVEL_SCOPE);
        NamingEnumeration<SearchResult> namingEnumeration =
                ctx.search("", LDAPConsts.OU_ALL, new Object[]{}, searchControls);
        LOGGER.log(Level.INFO, "---- Groups - OU -----");
        while (namingEnumeration.hasMore()) {
            SearchResult sr = namingEnumeration.next();
            LOGGER.log(Level.INFO, "-----------------------------------------------------------------------");
            LOGGER.log(Level.INFO, "SearchResult: " + sr);
        }
    }

    public int countUsers() throws NamingException {
        int counter = 0;
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> namingEnumeration =
                ctx.search("", LDAPConsts.UID_ALL, new Object[]{}, searchControls);
        while (namingEnumeration.hasMore()) {
            SearchResult sr = namingEnumeration.next();
            counter++;
        }
        return counter;
    }

    public void createUser(String userId, String name, String surname, String email) throws NamingException {
        Attributes attributes = new BasicAttributes();
        attributes.put(LDAPConsts.PERSON);
        attributes.put(LDAPConsts.ORGANIZATIONAL_PERSON);
        attributes.put(LDAPConsts.INET_ORG_PERSON);
        // attributes.put(LDAPConsts.POSSIX_ACCOUNT);
        //attributes.put(LDAPConsts.SHADOW_ACCOUNT);

        //String valueHomeDirectory = "/home/"+prefix;
        // Attribute attributeHomeDirectory = new BasicAttribute(LDAPConsts.HOME_DIRECTORY,valueHomeDirectory);
        Attribute attributeUid = new BasicAttribute(LDAPConsts.UID, userId);
        Attribute attributeCn = new BasicAttribute(LDAPConsts.CN, name);
        Attribute attributeSn = new BasicAttribute(LDAPConsts.SN, surname);
        Attribute attributeEmail = new BasicAttribute(LDAPConsts.EMAIL, email);

        String parameterPassword = "userPassword";
        String valuePassword = "-password";
        Attribute attributePassword = new BasicAttribute(parameterPassword, valuePassword);

        attributes.put(attributeSn);
        attributes.put(attributeCn);
        attributes.put(attributePassword);
        attributes.put(attributeUid);
        //attributes.put(attributeHomeDirectory);

        String dn = generateDnUser(userId);
        ctx.createSubcontext(dn, attributes);
        LOGGER.log(Level.INFO, String.format("Added user with ID: %s", userId));
    }

    public void searchUser(String userId) throws NamingException {
        String base = "ou=users";//,ou=BusinessObjectsDisaster,ou=system";
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String filter = "(&(objectclass=" + LDAPConsts.INETORGPERSON + ")(uid=" + userId + "))";
        NamingEnumeration<SearchResult> results = ctx.search(base, filter, searchControls);
        while (results.hasMore()) {
            SearchResult sr = results.next();
            Attributes attrs = sr.getAttributes();
            Attribute attr = attrs.get("uid");
            if (attr != null) {
                LOGGER.info("Record found : " + attr.get());
                LOGGER.info("Attrs        : " + attrs);
                //LOGGER.info("GID          : " + attrs.get("ou").get(0));
            }
        }
    }

    public void removeUser(String userId) throws NamingException {
        ctx.destroySubcontext(generateDnUser(userId));
        LOGGER.log(Level.INFO, String.format("Removed user with ID: %s", userId));
    }

    public void createGroup(String groupName) throws NamingException {
        Attributes attrs = new BasicAttributes(true);
        attrs.put("objectClass", "organizationalUnit");
        String groupDn = generateDnGroup(groupName);
        ctx.createSubcontext(groupDn, attrs);
    }

    public void removeGroup(String groupName) throws NamingException {
        ctx.destroySubcontext(generateDnGroup(groupName));
    }

    public void close() throws NamingException {
        ctx.close();
    }

    private String generateDnUser(String uid) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(LDAPConsts.UID).append("=").append(uid)
                //.append(",").append(LDAPConsts.OU).append("=").append("users")
                .toString();
    }

    private String generateDnGroup(String groupName) {
        return LDAPConsts.OU + "=" + groupName;//+ ",dc=example,dc=org";
    }

}