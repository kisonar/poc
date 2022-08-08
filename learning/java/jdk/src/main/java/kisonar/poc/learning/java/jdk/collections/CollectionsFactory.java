package kisonar.poc.learning.java.jdk.collections;

public final class CollectionsFactory {

      private CollectionsFactory() {
      }

      static Entity getEntityEqualsOkHashOk(int id, String name, String value) {
            return new EntityEqualsOkHashOk(id, name, value);
      }

      static Entity getEntityEqualsOkHashNok(int id, String name, String value) {
            return new EntityEqualsOkHashNok(id, name, value);
      }

      static Entity getEntityEqualsOkHashNokHardcoded(int id, String name, String value) {
            return new EntityEqualsOkHashNokHardcoded(id, name, value);
      }

      static Entity getEntityEqualsNokHashOk(int id, String name, String value) {
            return new EntityEqualsNokHashOk(id, name, value);
      }

      static Entity getEntityEqualsNokFalseHashNokHardcoded(int id, String name, String value) {
            return new EntityEqualsNokFalseHashNokHardcoded(id, name, value);
      }

      static Entity getEntityEqualsNokTrueHashNokHardcoded(int id, String name, String value) {
            return new EntityEqualsNokTrueHashNokHardcoded(id, name, value);
      }
}