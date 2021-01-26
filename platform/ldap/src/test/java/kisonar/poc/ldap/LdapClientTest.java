package kisonar.poc.ldap;

import org.junit.jupiter.api.*;

import kisonar.platform.domain.User;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;
import java.util.Optional;

@Disabled
public class LdapClientTest {

    private final static Hashtable<String, Object> env = new Hashtable<>();
    private DirContext ctx;
    private LdapClient ldapClient;
    private String groupNameNotDefined = "groupNotDefined";
    private String groupName = "mygroup";
    private User user = new User("mmigdal", "marcin", "migdal", "password");

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
    public void groups_CreateNewOne_Fetch_RemoveCreated() throws NamingException {
        Assertions.assertEquals(1, ldapClient.fetchGroups().size());

        ldapClient.createGroup(groupName);
        Assertions.assertEquals(2, ldapClient.fetchGroups().size());

        ldapClient.removeGroup(groupName);
        Assertions.assertEquals(1, ldapClient.fetchGroups().size());
    }

    @Test
    public void groups_CreateGroupWhichAlreadyExists() throws NamingException {
        Assertions.assertEquals(1, ldapClient.fetchGroups().size());
        ldapClient.createGroup(groupName);
        Assertions.assertEquals(2, ldapClient.fetchGroups().size());

        Assertions.assertThrows(javax.naming.NameAlreadyBoundException.class, () -> {
            ldapClient.createGroup(groupName);
        });

        ldapClient.removeGroup(groupName);
        Assertions.assertEquals(1, ldapClient.fetchGroups().size());
    }

    @Test
    public void groups_removeWhichDoesNotExist() throws NamingException {
        Assertions.assertEquals(1, ldapClient.fetchUsers().size());

        ldapClient.removeGroup(groupNameNotDefined);
        Assertions.assertEquals(1, ldapClient.fetchUsers().size());
        Assertions.assertEquals(1, ldapClient.fetchGroups().size());
    }

    @Test
    public void user_createUserWithDefinedGroup_removeUserWithDefinedGroup() throws NamingException {
        Assertions.assertEquals(1, ldapClient.fetchUsers().size());
        Assertions.assertEquals(1, ldapClient.fetchGroups().size());

        ldapClient.createGroup(groupName);
        Assertions.assertEquals(2, ldapClient.fetchGroups().size());

        ldapClient.createUser(user, groupName);
        Assertions.assertEquals(2, ldapClient.fetchUsers().size());
        Assertions.assertEquals(2, ldapClient.fetchGroups().size());

        ldapClient.removeUser(user.userId);
        Assertions.assertEquals(1, ldapClient.fetchUsers().size());
        Assertions.assertEquals(2, ldapClient.fetchGroups().size());

        ldapClient.removeGroup(groupName);
        Assertions.assertEquals(1, ldapClient.fetchUsers().size());
        Assertions.assertEquals(1, ldapClient.fetchGroups().size());
    }

    @Test
    public void user_addUserToNonExistingGroup() throws NamingException {
        Assertions.assertThrows(javax.naming.NameNotFoundException.class, () -> {
            ldapClient.createUser(user, groupNameNotDefined);
        });
    }

    @Test
    public void user_fetch() throws NamingException {
        ldapClient.fetchUsers();
    }

}