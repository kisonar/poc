package kisonar.poc.learning.java.jdk.time;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTest {

      @Test
      public void timeComparison() {
            Instant now = Instant.now();
            Instant earlier = now.minus(24, HOURS);
            long resultHours = HOURS.between(earlier, now);
            long resultMinutes = MINUTES.between(earlier, now);
            assertEquals(24, resultHours);
            assertEquals(1440, resultMinutes);
      }
}
