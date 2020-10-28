package kisonar.poc.ldap;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;


import java.util.Hashtable;

public class Main {

    public static void main(String[] args) throws NamingException {

        Hashtable<String, Object> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:1389/dc=example,dc=org");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=example,dc=org");
        env.put(Context.SECURITY_CREDENTIALS, "adminPswd");
        DirContext ctx = new InitialDirContext(env);

        LdapClient ldapClient = new LdapClient(ctx);
        ldapClient.listAll();
        ldapClient.addUser("", "");
        ldapClient.listAll();
        ldapClient.close();
    }
}