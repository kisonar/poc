package kisonar.poc.ldap;

public final class User {

    public final String userId;
    public final String name;
    public final String surname;
    public final String email;

    public User(String userId, String name, String surname, String email) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}