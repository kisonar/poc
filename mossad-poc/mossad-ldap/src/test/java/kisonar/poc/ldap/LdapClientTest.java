package kisonar.poc.ldap;

import org.junit.jupiter.api.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;
import java.util.Optional;

public class LdapClientTest {

    private final static Hashtable<String, Object> env = new Hashtable<>();
    private DirContext ctx;
    private LdapClient ldapClient;
    private String userName = "mandi";
    private String userSurname = "makumba";

    @BeforeEach
    public void beforeEach() throws NamingException {
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:1389/dc=example,dc=org");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=example,dc=org");
        env.put(Context.SECURITY_CREDENTIALS, "adminPswd");
        ctx = new InitialDirContext(env);
        ldapClient = new LdapClient(ctx);
    }

    @AfterEach
    public void afterEach() {
        Optional<LdapClient> ldapClientOptional = Optional.ofNullable(ldapClient);
        ldapClientOptional.ifPresent(item -> {
            try {
                item.close();
            } catch (NamingException e) {
                Assertions.fail(e);
            }
        });
    }

    @Test
    public void addUser() throws NamingException {
        ldapClient.addUser(userName, userSurname);
    }

    @Test
    public void listAfterAdding() throws NamingException {
        ldapClient.listAll();
    }


    @Test
    public void removeUser() throws NamingException {
        ldapClient.removeUser(userName);
    }

    @Test
    public void listAfterRemoval() throws NamingException {
        ldapClient.listAll();
    }

}