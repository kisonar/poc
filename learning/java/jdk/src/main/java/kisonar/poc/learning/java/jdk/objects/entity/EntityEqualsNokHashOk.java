package kisonar.poc.learning.java.jdk.objects.entity;

import java.util.Objects;

public final class EntityEqualsNokHashOk extends Entity {

      public EntityEqualsNokHashOk(int id, String name, String value) {
            super(id, name, value);
      }

    @SuppressWarnings({"should check the class of its parameter", "should check the class of its parameter", "This is a hardcoded implementation of equals method that always returns true, which is not recommended."})
    @Override
      public boolean equals(Object obj) {
            if (obj == this) {
                  return true;
            }

            if (!(obj instanceof Entity e)) return false;

            return Objects.equals(e.name, this.name) && Objects.equals(e.value, this.value);  //missing id
      }

}
