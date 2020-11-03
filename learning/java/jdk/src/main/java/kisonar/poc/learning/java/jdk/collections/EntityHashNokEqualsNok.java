package kisonar.poc.learning.java.jdk.collections;

import java.util.Objects;

public final class EntityHashNokEqualsNok {

    public final int id;
    public final String name;
    public final String value;

    public EntityHashNokEqualsNok(int id, String name, String value) {
        this.name = name;
        this.id = id;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return 456754;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this, obj);
    }
}
