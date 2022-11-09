package kisonar.poc.learning.java.jdk.functional.function;

import kisonar.poc.learning.java.jdk.collections.CollectionsFactory;
import kisonar.poc.learning.java.jdk.collections.entity.EntityEqualsOkHashOk;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecordFunctionsTest {

      private final int id = 1;
      private final String name = "name";
      private final String value = "value";

      @Test
      public void convert() {
            var entity = (EntityEqualsOkHashOk) CollectionsFactory.getEntityEqualsOkHashOk(id, name, value);

            var record = RecordFunctions.convert.apply(entity);

            assertEquals(record.id(), entity.id);
            assertEquals(record.name(), entity.name);
            assertEquals(record.value(), entity.value);
      }

      @Test
      public void copy() {
            String anotherName = "anotherName";
            String anotherValue = "anotherValue";
            var entity = (EntityEqualsOkHashOk) CollectionsFactory.getEntityEqualsOkHashOk(id, name, value);
            var entityAnother = (EntityEqualsOkHashOk) CollectionsFactory.getEntityEqualsOkHashOk(id, anotherName, anotherValue);

            var record = RecordFunctions.copyValuesFromSecondToFirst.apply(entity, entityAnother);

            assertEquals(record.id(), entity.id);
            assertEquals(record.name(), anotherName);
            assertEquals(record.value(), anotherValue);
      }
}