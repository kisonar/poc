package kisonar.poc.learning.java.jdk.collections;

public final class EntityEqualsOkHashNok extends Entity {

    public EntityEqualsOkHashNok(int id, String name, String value) {
        super(id, name, value);
    }

    @Override
    public int hashCode() {
        return 4; //missing value
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
