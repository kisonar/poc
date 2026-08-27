package kisonar.poc.learning.java.jdk.objects.entity;

public final class EntityEqualsNokTrueHashNokHardcoded extends Entity {

      public EntityEqualsNokTrueHashNokHardcoded(int id, String name, String value) {
            super(id, name, value);
      }

      @Override
      public int hashCode() {
            return 4;
      }

      @SuppressWarnings({"should check the class of its parameter", "This is a hardcoded implementation of equals method that always returns true, which is not recommended."})
      @Override
      public boolean equals(Object obj) {
            return true;
      }
}
