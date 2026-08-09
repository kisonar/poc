package kisonar.platform.domain.user;

public record UserRecord(String ldapId, String name, String surname, String password, String email) {
}