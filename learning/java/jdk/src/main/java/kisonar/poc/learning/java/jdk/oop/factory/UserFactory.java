package kisonar.poc.learning.java.jdk.oop.factory;

import kisonar.platform.domain.user.UserRecord;

public class UserFactory {

      private UserFactory() {
      }

      public static UserRecord getUser(String id, String name, String surname, String password, String email) {
            return new UserRecord(id, name, surname, password, email);
      }

}