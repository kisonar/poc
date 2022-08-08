package kisonar.poc.learning.java.jdk.collections;

import java.util.Objects;

public final class EntityEqualsOkHashNok extends Entity {

    public EntityEqualsOkHashNok(int id, String name, String value) {
        super(id, name, value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value); //missing id
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
