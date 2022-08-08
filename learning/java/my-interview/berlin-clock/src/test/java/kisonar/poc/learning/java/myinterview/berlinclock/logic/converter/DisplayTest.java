package kisonar.poc.learning.java.myinterview.berlinclock.logic.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

public class DisplayTest {

      private final Converter converter = Mockito.mock(Converter.class);
      private Display display;

      @BeforeEach
      public void setUp() {
            display = new Display(converter);
      }

      //TODO
      @Disabled
      @Test
      public void fetchResult() {
            LocalTime localTime = Mockito.mock(LocalTime.class);
            ConvertionResult convertionResult = Mockito.mock(ConvertionResult.class);
            Mockito.when(converter.convertToBerlinClockFormat(localTime)).thenReturn(convertionResult);

            int result = display.fetchResult(localTime);
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
