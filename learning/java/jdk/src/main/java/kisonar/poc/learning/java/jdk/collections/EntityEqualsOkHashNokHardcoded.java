package kisonar.poc.learning.java.jdk.collections;

public final class EntityEqualsOkHashNokHardcoded extends Entity {

    public EntityEqualsOkHashNokHardcoded(int id, String name, String value) {
        super(id, name, value);
    }

    @Override
    public int hashCode() {
        return 4;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
