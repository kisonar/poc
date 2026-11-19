package kisonar.platform.domain.user.factory;

import kisonar.platform.domain.user.User;

public class UserFactory {

    private UserFactory() {
    }

    public static User getUser(String id, String name, String surname, String password, String email) {
        return new User(id, name, surname, password, email);
    }

}