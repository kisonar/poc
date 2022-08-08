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

      static Entity getEntityEqualsNokHashOk(int id, String name, String value) {
            return new EntityEqualsNokHashOk(id, name, value);
      }

      static Entity getEntityEqualsNokHashNok(int id, String name, String value) {
            return new EntityEqualsNokHashNok(id, name, value);
      }
}