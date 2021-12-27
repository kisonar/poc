package kisonar.platform.domain.user;

public record User(String ldapId, String name, String surname, String password, String email) {}