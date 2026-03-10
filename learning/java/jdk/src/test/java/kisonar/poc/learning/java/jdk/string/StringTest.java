package kisonar.poc.learning.java.jdk.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringTest {

      private final String something = " something ";

      @Test
      public void string_join() {
            String anything = " anything ";

            String result = String.join("|", something, anything);

            assertEquals(" something | anything ", result);
      }

      @Test
      public void string_format() {
            var result = String.format("Input %s output", "Yes");

            assertEquals("Input Yes output", result);
      }

      @Test
      public void string_strip() {
            assertEquals("something", something.strip());
            assertEquals("something ", something.stripLeading());
            assertEquals(" something", something.stripTrailing());
            assertEquals("something", something.stripIndent());
      }
}