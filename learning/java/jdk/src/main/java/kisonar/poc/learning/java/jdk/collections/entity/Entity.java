package kisonar.poc.learning.java.jdk.collections.entity;

import java.util.Objects;

public class Entity {

      public final int id;
      public final String name;
      public final String value;

      public Entity(int id, String name, String value) {
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
            if (obj == this) {
                  return true;
            }

            if (!(obj instanceof Entity e)) return false;

            return this.id == e.id && Objects.equals(e.name, this.name) && Objects.equals(e.value, this.value);
      }
}
