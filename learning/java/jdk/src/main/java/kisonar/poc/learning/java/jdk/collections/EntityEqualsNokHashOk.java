package kisonar.poc.learning.java.jdk.collections;

import java.util.Objects;

public final class EntityEqualsNokHashOk extends Entity {

      public EntityEqualsNokHashOk(int id, String name, String value) {
            super(id, name, value);
      }

      @Override
      public int hashCode() {
            return Objects.hash(id, name, value);
      }

      @SuppressWarnings("should check the class of its parameter")
      @Override
      public boolean equals(Object obj) {
            if (obj == this) {
                  return true;
            }

            if (!(obj instanceof Entity e)) return false;

            return this.id == e.id && Objects.equals(e.name, this.name);  //missing value
      }

}
