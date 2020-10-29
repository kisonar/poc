package kisonar.poc.ldap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

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
    private String userId = "mmigdal";
    private String userName = "marcin";
    private String userSurname = "migdal";
    private String userEmail = "kisonar@wp.pl";
    private String groupName = "mygroup";
    private String groupDescription = "myGroupDescription";

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
    public void createGroup() throws NamingException {
        ldapClient.createGroup(groupName);
    }

    @Test
    public void listGroups() throws NamingException {
        ldapClient.listGroups();
    }

    @Test
    public void removeGroup() throws NamingException {
        ldapClient.removeGroup(groupName);
    }


    @Test
    public void createUser() throws NamingException {
        ldapClient.createUser(userId, userName, userSurname, userEmail);
    }

    @Test
    public void listUsersAfterUserCreation() throws NamingException {
        ldapClient.listUsers();
    }

    @Test
    public void searchUser() throws NamingException {
        ldapClient.searchUser(userId);
    }

    @Test
    public void removeUser() throws NamingException {
        ldapClient.removeUser(userId);
    }

    @Test
    public void listUsersAfterUserRemoval() throws NamingException {
        ldapClient.listUsers();
    }
}