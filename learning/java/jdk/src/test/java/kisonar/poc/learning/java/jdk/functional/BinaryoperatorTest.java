package kisonar.poc.learning.java.jdk.functional;

import org.junit.jupiter.api.Test;

import java.util.function.BinaryOperator;

public class BinaryoperatorTest {

      private final BinaryOperator<Integer> binaryOperator = (val1, val2) -> val1 * val2;


      @Test
      public void testBinaryOperator() {
            var result = binaryOperator.apply(2, 3);

            assert result == 6;
      }
}
