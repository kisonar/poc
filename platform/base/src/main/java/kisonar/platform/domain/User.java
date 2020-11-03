package kisonar.platform.domain;

public final class User {

    public final String userId;
    public final String name;
    public final String surname;
    public final String password;

    public User(String userId, String name, String surname, String password) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }
}