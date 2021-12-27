package kisonar.poc.ldap;

import kisonar.platform.domain.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@Disabled
public class LdapClientTest {

    private final static Hashtable<String, Object> env = new Hashtable<>();
    private DirContext ctx;
    private LdapClient ldapClient;
    private final String groupNameNotDefined = "groupNotDefined";
    private final String groupName = "mygroup";
    private final User user = new User("mmigdal", "marcin", "migdal", "password", "marcin.migdal@email.com");

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
                fail(e);
            }
        });
    }

    @Test
    public void createGroup_FetchCreatedGroup_RemoveCreatedGroup() throws NamingException {
        assertEquals(1, ldapClient.fetchGroups().size());

        ldapClient.createGroup(groupName);
        assertEquals(2, ldapClient.fetchGroups().size());

        ldapClient.removeGroup(groupName);
        assertEquals(1, ldapClient.fetchGroups().size());
    }

    @Test
    public void createGroup_createGroupWhichAlreadyExists_removeGroup() throws NamingException {
        assertEquals(1, ldapClient.fetchGroups().size());
        ldapClient.createGroup(groupName);
        assertEquals(2, ldapClient.fetchGroups().size());

        assertThrows(javax.naming.NameAlreadyBoundException.class, () -> ldapClient.createGroup(groupName));

        ldapClient.removeGroup(groupName);
        assertEquals(1, ldapClient.fetchGroups().size());
    }

    @Test
    public void removeGroupWhichDoesNotExist() throws NamingException {
        assertEquals(1, ldapClient.fetchUsers().size());

        ldapClient.removeGroup(groupNameNotDefined);
        assertEquals(1, ldapClient.fetchUsers().size());
        assertEquals(1, ldapClient.fetchGroups().size());
    }

    @Test
    public void createUserWithDefinedGroupAndNextRemoveUserWithDefinedGroup() throws NamingException {
        assertEquals(1, ldapClient.fetchUsers().size());
        assertEquals(1, ldapClient.fetchGroups().size());

        ldapClient.createGroup(groupName);
        assertEquals(2, ldapClient.fetchGroups().size());

        ldapClient.createUser(user, groupName);
        assertEquals(2, ldapClient.fetchUsers().size());
        assertEquals(2, ldapClient.fetchGroups().size());

        ldapClient.removeUser(user.ldapId());
        assertEquals(1, ldapClient.fetchUsers().size());
        assertEquals(2, ldapClient.fetchGroups().size());

        ldapClient.removeGroup(groupName);
        assertEquals(1, ldapClient.fetchUsers().size());
        assertEquals(1, ldapClient.fetchGroups().size());
    }

    @Test
    public void addUserToNonExistingGroup() throws NamingException {
        assertThrows(javax.naming.NameNotFoundException.class, () -> ldapClient.createUser(user, groupNameNotDefined));
    }

    @Test
    public void fetchUsers() throws NamingException {
        ldapClient.fetchUsers();
    }

}