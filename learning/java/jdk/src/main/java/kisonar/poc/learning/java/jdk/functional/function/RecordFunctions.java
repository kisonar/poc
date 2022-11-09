package kisonar.poc.learning.java.jdk.functional.function;

import kisonar.poc.learning.java.jdk.collections.entity.EntityEqualsOkHashOk;
import kisonar.poc.learning.java.jdk.collections.record.RecordEntity;

import java.util.function.BiFunction;
import java.util.function.Function;

public final class RecordFunctions {

      public static Function<EntityEqualsOkHashOk, RecordEntity> convert = (input) -> new RecordEntity(input.id, input.name, input.value);
      public static BiFunction<EntityEqualsOkHashOk, EntityEqualsOkHashOk, RecordEntity> copyValuesFromSecondToFirst =
              (inputFirst, inputSecond) -> new RecordEntity(inputFirst.id, inputSecond.name, inputSecond.value);

      private RecordFunctions() {
      }
}