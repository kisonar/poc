package kisonar.poc.learning.java.jdk.time;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTest {

      @Test
      public void timeComparison() {
            Instant now = Instant.now();
            Instant earlier = now.minus(24, ChronoUnit.HOURS);
            long resultHours = ChronoUnit.HOURS.between(earlier, now);
            long resultMinutes = ChronoUnit.MINUTES.between(earlier, now);
            assertEquals(24, resultHours);
            assertEquals(1440, resultMinutes);
      }
}
