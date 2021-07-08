package kisonar.platform.domain.factory;

import kisonar.platform.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserFactoryTest {

    @Test
    public void canCreateUser() {
        String ldapId = "ldapId";
        String userName = "userName";
        String userSurname = "userSurname";
        String userPassword = "userPassword";
        String userEmail = "user@user.com";

        User user = UserFactory.getUser(ldapId, userName, userSurname, userPassword, userEmail);

        assertEquals(ldapId, user.ldapId());
        assertEquals(userName, user.name());
        assertEquals(userSurname, user.surname());
        assertEquals(userPassword, user.password());
        assertEquals(userEmail, user.email());

    }
}