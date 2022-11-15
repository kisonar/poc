package kisonar.poc.learning.java.jdk.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringTest {

      private final String something = " something ";
      private final String anything = " anything ";
      private final String nothing = "    ";
      private final String whatecer = " whatever anywhere   ";

      @Test
      public void join() {
            String result = String.join("|", something, anything);
            assertEquals(" something | anything ", result);
      }

      public String strip(String value) {
            return value.strip();
      }

      public String stripLeading(String value) {
            return value.stripLeading();
      }

      public String stripTrailing(String value) {
            return value.stripTrailing();
      }

      public String stripIndent(String value) {
            return value.stripIndent();
      }

}
