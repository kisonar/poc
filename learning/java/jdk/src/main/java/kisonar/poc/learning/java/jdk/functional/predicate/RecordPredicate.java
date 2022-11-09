package kisonar.poc.learning.java.jdk.functional.predicate;

import kisonar.poc.learning.java.jdk.collections.record.RecordEntity;

import java.util.function.Predicate;

public final class RecordPredicate {

      public static final Predicate<RecordEntity> recordEntityPredicateNameNotEmpty = (item) -> !item.name().isEmpty();
      public static final Predicate<RecordEntity> recordEntityPredicateNameNotBlank = (item) -> !item.name().isBlank();

      private RecordPredicate() {
      }
}
