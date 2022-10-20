package kisonar.poc.learning.java.jdk.ternary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TernaryTest {

      @Test
      public void ternaryWithFunctionsWhenFalse() {
            var value = false;
            String result = value ? getTrue() : getFalse();
            assertEquals("false", result);
      }

      @Test
      public void ternaryWithFunctionsWhenTrue() {
            var value = true;
            String result = value ? getTrue() : getFalse();
            assertEquals("true", result);
      }

      private String getTrue() {
            return "true";
      }

      private String getFalse() {
            return "false";
      }
}
