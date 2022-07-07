package kisonar.poc.learning.java.jdk.collections;

public final class CollectionsFactory {

      private CollectionsFactory() {
      }

      static Entity getEntityOK(int id, String name, String value) {
            return new EntityOK(id, name, value);
      }

      static Entity getEntityHashNokEqualsNok(int id, String name, String value) {
            return new EntityHashNokEqualsNok(id, name, value);
      }

      static Entity getEntityHashNokEqualsOk(int id, String name, String value) {
            return new EntityHashNokEqualsOk(id, name, value);
      }

      static Entity getEntityHashOkEqualsNok(int id, String name, String value) {
            return new EntityHashOkEqualsNok(id, name, value);
      }

}
