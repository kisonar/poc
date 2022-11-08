package kisonar.poc.learning.java.jdk.collections;

import kisonar.poc.learning.java.jdk.collections.entity.Entity;
import kisonar.poc.learning.java.jdk.collections.entity.EntityEqualsNokFalseHashNokHardcoded;
import kisonar.poc.learning.java.jdk.collections.entity.EntityEqualsNokHashOk;
import kisonar.poc.learning.java.jdk.collections.entity.EntityEqualsNokTrueHashNokHardcoded;
import kisonar.poc.learning.java.jdk.collections.entity.EntityEqualsOkHashNok;
import kisonar.poc.learning.java.jdk.collections.entity.EntityEqualsOkHashNokHardcoded;
import kisonar.poc.learning.java.jdk.collections.entity.EntityEqualsOkHashOk;
import kisonar.poc.learning.java.jdk.collections.record.RecordEntity;

public final class CollectionsFactory {

      private CollectionsFactory() {
      }

      public static Entity getEntityEqualsOkHashOk(int id, String name, String value) {
            return new EntityEqualsOkHashOk(id, name, value);
      }

      public static Entity getEntityEqualsOkHashNok(int id, String name, String value) {
            return new EntityEqualsOkHashNok(id, name, value);
      }

      public static Entity getEntityEqualsOkHashNokHardcoded(int id, String name, String value) {
            return new EntityEqualsOkHashNokHardcoded(id, name, value);
      }

      public static Entity getEntityEqualsNokHashOk(int id, String name, String value) {
            return new EntityEqualsNokHashOk(id, name, value);
      }

      public static Entity getEntityEqualsNokFalseHashNokHardcoded(int id, String name, String value) {
            return new EntityEqualsNokFalseHashNokHardcoded(id, name, value);
      }

      public static Entity getEntityEqualsNokTrueHashNokHardcoded(int id, String name, String value) {
            return new EntityEqualsNokTrueHashNokHardcoded(id, name, value);
      }

      public static RecordEntity getRecord(int id, String name, String value) {
            return new RecordEntity(id, name, value);
      }
}