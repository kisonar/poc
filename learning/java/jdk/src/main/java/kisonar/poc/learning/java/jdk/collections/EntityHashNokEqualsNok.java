package kisonar.poc.learning.java.jdk.collections;

import java.util.Objects;

public final class EntityHashNokEqualsNok extends Entity {

    public EntityHashNokEqualsNok(int id, String name, String value) {
        super(id, name, value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @SuppressWarnings("should check the class of its parameter")
    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
