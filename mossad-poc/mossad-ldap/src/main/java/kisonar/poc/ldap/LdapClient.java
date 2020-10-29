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

import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LdapClient {

    private final static Logger LOGGER = Logger.getLogger(LdapClient.class.getClass().getName());
    private DirContext ctx;

    public LdapClient(DirContext ctx) {
        this.ctx = ctx;
    }

    public void listAll() throws NamingException {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> namingEnumeration =
                ctx.search("", LDAPConsts.UID_ALL, new Object[]{}, searchControls);
        while (namingEnumeration.hasMore()) {
            SearchResult sr = namingEnumeration.next();
            LOGGER.log(Level.INFO, "-----------------------------------------------------------------------");
            LOGGER.log(Level.INFO, "SearchResult: " + sr);
            // LOGGER.log(Level.INFO, "Name in namespace :" + sr.getNameInNamespace());
            //LOGGER.log(Level.INFO, "DN: " + sr.getName());
            //LOGGER.log(Level.INFO, "UID: " + sr.getAttributes().get("uid"));
            //LOGGER.log(Level.INFO, "dc: " + sr.getAttributes().get("dc"));
            //LOGGER.log(Level.INFO, "Password encoded by bytes: " + sr.getAttributes().get("userPassword").get());
            // LOGGER.log(Level.INFO, "Password:" + new String((byte[]) sr.getAttributes().get("userPassword").get()));
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


    public void addUser(String userId, String name, String surname, String email) throws NamingException {
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

        String dn = generateDn(userId);
        ctx.createSubcontext(dn, attributes);
        LOGGER.log(Level.INFO, String.format("Added user with ID: %s", userId));
    }

    public void removeUser(String userId) throws NamingException {
        ctx.destroySubcontext(generateDn(userId));
        LOGGER.log(Level.INFO, String.format("Removed user with ID: %s", userId));
    }

    public void close() throws NamingException {
        ctx.close();
    }

    private String generateDn(String uid) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(LDAPConsts.UID).append("=").append(uid).append(",").append(LDAPConsts.OU).append("=").append("users").toString();
    }
}