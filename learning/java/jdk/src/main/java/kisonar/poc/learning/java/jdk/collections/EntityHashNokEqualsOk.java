package kisonar.poc.learning.java.jdk.collections;

import java.util.Objects;

public final class EntityHashNokEqualsOk extends EntityHashOkEqualsOk {

    public EntityHashNokEqualsOk(int id, String name, String value) {
        super(id, name, value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value); //missing name
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this, obj);
    }
}
