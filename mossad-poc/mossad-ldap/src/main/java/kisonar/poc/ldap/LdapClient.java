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


public class LdapClient {

    private DirContext ctx;

    public LdapClient(DirContext ctx) {
        this.ctx = ctx;
    }

    public void listAll() throws NamingException {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> namingEnumeration =
                ctx.search("", "(uid=*)", new Object[]{}, searchControls);
        while (namingEnumeration.hasMore()) {
            SearchResult sr = namingEnumeration.next();
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("SearchResult: " + sr);
            System.out.println("Name in namespace :" + sr.getNameInNamespace());
            System.out.println("DN: " + sr.getName());
            System.out.println("UID: " + sr.getAttributes().get("uid"));
            System.out.println("dc: " + sr.getAttributes().get("dc"));
            System.out.println("Password encoded by bytes: " + sr.getAttributes().get("userPassword").get());
            System.out.println("Password:" + new String((byte[]) sr.getAttributes().get("userPassword").get()));
        }
    }

    public void addUser(String name2, String surname2) throws NamingException {

        Attributes attributes = new BasicAttributes();
        attributes.put(LDAPConsts.PERSON);
        attributes.put(LDAPConsts.ORGANIZATIONAL_PERSON);
        attributes.put(LDAPConsts.INET_ORG_PERSON);

        Attribute snAttribute = new BasicAttribute("sn", "testowy-sn");
        String parameterCn = "cn";
        String valueCn = "testowy-common-name";
        Attribute attributeCn = new BasicAttribute(parameterCn, valueCn);

        String valuePassword = "pass";
        String parameterPassword = "userPassword";
        String name = "testowy";
        Attribute attributePassword = new BasicAttribute(parameterPassword, valuePassword);

        attributes.put(snAttribute);
        attributes.put(attributeCn);
        attributes.put(attributePassword);
        String dn = "uid=" + name + ",ou=users";
        ctx.createSubcontext(dn, attributes);
        System.out.println("User added");
    }

    public void close() throws NamingException {
        ctx.close();
    }
}