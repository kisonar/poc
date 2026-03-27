package kisonar.poc.learning.java.myinterview.berlinclock.logic.converter;

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
      void shouldTransformMidnightToDisplayDataWithNoActivatedHourOrMinuteLamps() {
            var displayData = converter.transformLocalTimeToDisplayData(LocalTime.MIDNIGHT);

            Assertions.assertEquals(LocalTime.MIDNIGHT, displayData.localTime());
            Assertions.assertEquals(0, displayData.displayDataHours().activatedLampsHoursAtTop());
            Assertions.assertEquals(0, displayData.displayDataHours().activatedLampsHoursAtBottom());
            Assertions.assertEquals(0, displayData.displayDataMinutes().activatedLampsMinutesAtTop());
            Assertions.assertEquals(0, displayData.displayDataMinutes().activatedLampsMinutesAtBottom());
      }

      @Test
      void shouldTransformTimeToDisplayDataUsingFiveAndSingleUnitRanges() {
            var time = LocalTime.of(16, 44);

            var displayData = converter.transformLocalTimeToDisplayData(time);

            Assertions.assertEquals(time, displayData.localTime());
            Assertions.assertEquals(3, displayData.displayDataHours().activatedLampsHoursAtTop());
            Assertions.assertEquals(1, displayData.displayDataHours().activatedLampsHoursAtBottom());
            Assertions.assertEquals(8, displayData.displayDataMinutes().activatedLampsMinutesAtTop());
            Assertions.assertEquals(4, displayData.displayDataMinutes().activatedLampsMinutesAtBottom());
      }

      @Test
      void shouldTransformLastMinuteOfDayToMaximumDisplayDataRanges() {
            var time = LocalTime.of(23, 59);

            var displayData = converter.transformLocalTimeToDisplayData(time);

            Assertions.assertEquals(time, displayData.localTime());
            Assertions.assertEquals(4, displayData.displayDataHours().activatedLampsHoursAtTop());
            Assertions.assertEquals(3, displayData.displayDataHours().activatedLampsHoursAtBottom());
            Assertions.assertEquals(11, displayData.displayDataMinutes().activatedLampsMinutesAtTop());
            Assertions.assertEquals(4, displayData.displayDataMinutes().activatedLampsMinutesAtBottom());
      }

      @SuppressWarnings("DataFlowIssue")
      @Test
      void shouldThrowExceptionWhenTimeIsNull() {
            Assertions.assertThrows(NullPointerException.class, () -> converter.transformLocalTimeToDisplayData(null));
      }
}
