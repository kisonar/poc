package kisonar.platform.domain;

public final class User {

    public final String ldapId;
    public final String name;
    public final String surname;
    public final String password;

    public User(String userId, String name, String surname, String password) {
        this.ldapId = userId;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }
}