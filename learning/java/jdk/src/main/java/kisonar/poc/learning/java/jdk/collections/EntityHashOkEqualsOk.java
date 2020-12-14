package kisonar.poc.learning.java.jdk.collections;

import java.util.Objects;

public class EntityHashOkEqualsOk {

    public final int id;
    public final String name;
    public final String value;

    public EntityHashOkEqualsOk(int id, String name, String value) {
        this.name = name;
        this.id = id;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value);
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this, obj);
    }
}
