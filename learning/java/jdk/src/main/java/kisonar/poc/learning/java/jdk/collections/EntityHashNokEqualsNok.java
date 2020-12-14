package kisonar.poc.learning.java.jdk.collections;

import java.util.Objects;

public final class EntityHashNokEqualsNok extends EntityHashOkEqualsOk {

    public EntityHashNokEqualsNok(int id, String name, String value) {
        super(id, name, value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
