package kisonar.poc.learning.java.myinterview.berlinclock.logic.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class DisplayTest {

      private final Converter converter = new Converter();
      private Display display;

      @BeforeEach
      public void setUp() {
            display = new Display(converter);
      }

      @Test
      public void fetchResult() {
            int result = display.fetchResult(LocalTime.now());
            Assertions.assertEquals(0, result);
      }

      @Test
      public void fetchSum() {
            int result = display.fetchSum(3, 24);
            Assertions.assertEquals(27, result);
      }

      @Test
      public void fetchSumImproper() {
            int result = display.fetchSum(3, 24);
            Assertions.assertNotEquals(29, result);
      }

}
