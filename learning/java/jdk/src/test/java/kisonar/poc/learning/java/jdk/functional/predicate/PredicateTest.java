package kisonar.poc.learning.java.jdk.functional.predicate;

import kisonar.poc.learning.java.jdk.collections.CollectionsFactory;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PredicateTest {

      @Test
      public void predicates() {
            var result = IntStream.range(1, 9)
                    .mapToObj(item -> CollectionsFactory.getRecord(item, "", ""))
                    .filter(RecordPredicate.recordEntityPredicateNameNotBlank)
                    .collect(Collectors.toList());
            assertTrue(result.isEmpty());
      }
}
