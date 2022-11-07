package kisonar.poc.learning.java.jdk.collections.entity;

public final class EntityEqualsNokFalseHashNokHardcoded extends Entity {

    public EntityEqualsNokFalseHashNokHardcoded(int id, String name, String value) {
        super(id, name, value);
    }

    @Override
    public int hashCode() {
        return 4;
    }

    @SuppressWarnings("should check the class of its parameter")
    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
