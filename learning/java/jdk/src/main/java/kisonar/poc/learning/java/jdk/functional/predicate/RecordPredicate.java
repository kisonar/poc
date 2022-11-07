package kisonar.poc.learning.java.jdk.functional.predicate;

import kisonar.poc.learning.java.jdk.collections.record.RecordEntity;

import java.util.function.Predicate;

public class RecordPredicate {

      public final Predicate<RecordEntity> recordEntityPredicateNameNotEmpty = (item) -> !item.name().isEmpty();

      public final Predicate<RecordEntity> recordEntityPredicateNameNotBlank = (item) -> !item.name().isBlank();
}
