package kisonar.poc.learning.java.jdk.collections;

import java.util.Objects;

public final class EntityHashOkEqualsNok extends EntityHashOkEqualsOk {

    public EntityHashOkEqualsNok(int id, String name, String value) {
        super(id, name, value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value);
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
