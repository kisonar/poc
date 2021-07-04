package kisonar.poc.learning.java.myinterview.berlinclock.logic.converter;

import kisonar.poc.learning.java.myinterview.berlinclock.logic.converter.Converter;
import kisonar.poc.learning.java.myinterview.berlinclock.logic.converter.ConvertionResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class ConverterTest {

  private Converter converter;

  @BeforeEach
  void setUp() {
    converter = new Converter();
  }

  @Test
  void convert() {
    ConvertionResult convertionResult = converter.convertToBerlinClockFormat(LocalTime.of(16, 44));

    Assertions.assertEquals(16, convertionResult.hours);
    Assertions.assertEquals(44, convertionResult.minutes);

    Assertions.assertEquals(3, convertionResult.hoursRangeFive);
    Assertions.assertEquals(1, convertionResult.hoursRangeOne);

    Assertions.assertEquals(8, convertionResult.minutesRangeFive);
    Assertions.assertEquals(4, convertionResult.minutesRangeOne);
  }
}
