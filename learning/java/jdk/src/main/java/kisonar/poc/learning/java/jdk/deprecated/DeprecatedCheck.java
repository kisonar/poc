package kisonar.poc.learning.java.jdk.deprecated;

public final class DeprecatedCheck {

    private DeprecatedCheck() {
    }

    @Deprecated(forRemoval = true, since = "2.3")
    public static void check() {
    }
}
