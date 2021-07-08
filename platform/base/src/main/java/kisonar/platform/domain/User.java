package kisonar.platform.domain;

public record User(String ldapId, String name, String surname, String password, String email) {}