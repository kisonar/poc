package kisonar.poc.ldap;

import kisonar.platform.domain.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Hashtable;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@Disabled
public class LdapClientTest {

    private final Hashtable<String, Object> env = new Hashtable<>();
    private final String groupNameNotDefined = "groupNotDefined";
    private final String groupName = "mygroup";
    private final User user = new User("mmigdal", "marcin", "migdal", "password", "marcin.migdal@email.com");
    private LdapClient ldapClient;
    private static final String MALICIOUS_UID = "*)(uid=*))(|(uid=*";


    @BeforeEach
    public void beforeEach() throws NamingException {
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:1389/dc=example,dc=org");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=example,dc=org");
        env.put(Context.SECURITY_CREDENTIALS, "adminPswd");
        var ctx = new InitialDirContext(env);
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


    @Test
    public void findUserPassesUserIdAsFilterArgumentNotInFilterString() throws NamingException {
        DirContext ctx = mock(DirContext.class);
        NamingEnumeration<SearchResult> groups = mock(NamingEnumeration.class);
        NamingEnumeration<SearchResult> users = mock(NamingEnumeration.class);
        when(groups.hasMore()).thenReturn(false);
        when(users.hasMore()).thenReturn(false);
        when(ctx.search(anyString(), anyString(), any(Object[].class), any(SearchControls.class)))
                .thenReturn(groups);
        when(ctx.search(anyString(), eq("(&(objectclass=inetorgperson)(uid={0}))"),
                any(Object[].class), any(SearchControls.class))).thenReturn(users);

        assertFalse(new LdapClient(ctx).findUser(MALICIOUS_UID).isPresent());

        ArgumentCaptor<String> filter = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object[]> args = ArgumentCaptor.forClass(Object[].class);
        // first search lists groups, second is the user lookup
        verify(ctx, times(2)).search(anyString(), filter.capture(), args.capture(), any(SearchControls.class));
        assertEquals("(&(objectclass=inetorgperson)(uid={0}))", filter.getAllValues().get(1));
        assertArrayEquals(new Object[]{MALICIOUS_UID}, args.getAllValues().get(1));
    }
}