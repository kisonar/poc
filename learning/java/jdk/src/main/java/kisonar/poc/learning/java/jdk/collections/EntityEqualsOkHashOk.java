package kisonar.poc.learning.java.jdk.collections;

public class EntityEqualsOkHashOk extends Entity {

    public EntityEqualsOkHashOk(int id, String name, String value) {
        super(id, name, value);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
