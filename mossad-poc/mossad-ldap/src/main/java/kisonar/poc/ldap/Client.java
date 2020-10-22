package kisonar.poc.ldap;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import java.util.Hashtable;

public class Client {

    public static void main(String[] args) throws NamingException {

        Hashtable<String, Object> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:1389/dc=example,dc=org");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=example,dc=org");
        env.put(Context.SECURITY_CREDENTIALS, "adminPswd");
        DirContext ctx = new InitialDirContext(env);
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchControls.setCountLimit(10);
        NamingEnumeration<SearchResult> namingEnumeration =
                ctx.search("", "(uid=*)", new Object[]{}, searchControls);
        while (namingEnumeration.hasMore()) {
            SearchResult sr = namingEnumeration.next();
            System.out.println("DN: " + sr.getName());
            System.out.println(sr.getAttributes().get("uid"));
            System.out.println(sr.getAttributes().get("userPassword").get());
            System.out.println("Password:" + new String((byte[]) sr.getAttributes().get("userPassword").get()));
        }
        ctx.close();
    }
}