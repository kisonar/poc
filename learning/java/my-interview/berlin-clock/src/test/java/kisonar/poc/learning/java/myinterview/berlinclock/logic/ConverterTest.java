package kisonar.poc.learning.java.myinterview.berlinclock.logic;

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
        Result result = converter.convertToBerlinClockFormat(LocalTime.of(16, 44));

        Assertions.assertEquals(16, result.hours);
        Assertions.assertEquals(44, result.minutes);

        Assertions.assertEquals(3, result.hoursRangeFive);
        Assertions.assertEquals(1, result.hoursRangeOne);

        Assertions.assertEquals(8, result.minutesRangeFive);
        Assertions.assertEquals(4, result.minutesRangeOne);
    }
}
