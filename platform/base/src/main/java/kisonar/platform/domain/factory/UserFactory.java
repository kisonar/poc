package kisonar.platform.domain.factory;

import kisonar.platform.domain.User;

public class UserFactory {

    private UserFactory() {
    }

    public static User getUser(String id, String name, String surname, String password, String email) {
        return new User(id, name, surname, password, email);
    }

}