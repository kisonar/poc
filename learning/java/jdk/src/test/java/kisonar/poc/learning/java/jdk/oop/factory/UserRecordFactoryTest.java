package kisonar.poc.learning.java.jdk.oop.factory;

import kisonar.platform.domain.user.UserRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRecordFactoryTest {

      @Test
      public void canCreateUser() {
            String ldapId = "ldapId";
            String userName = "userName";
            String userSurname = "userSurname";
            String userPassword = "userPassword";
            String userEmail = "user@user.com";

            UserRecord userRecord = UserFactory.getUser(ldapId, userName, userSurname, userPassword, userEmail);

            assertEquals(ldapId, userRecord.ldapId());
            assertEquals(userName, userRecord.name());
            assertEquals(userSurname, userRecord.surname());
            assertEquals(userPassword, userRecord.password());
            assertEquals(userEmail, userRecord.email());

      }
}