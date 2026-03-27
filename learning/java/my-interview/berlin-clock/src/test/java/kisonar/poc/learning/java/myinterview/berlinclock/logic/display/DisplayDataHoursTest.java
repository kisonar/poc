package kisonar.poc.learning.java.myinterview.berlinclock.logic.display;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DisplayDataHoursTest {

      @Test
      public void getLampsWhenTopHas2ActivatedAndBottomHas3Activated() {
            var displayDataHours = new DisplayDataHours(2, 3);

            Assertions.assertEquals(2, displayDataHours.activatedLampsHoursAtTop());
            Assertions.assertEquals(2, displayDataHours.getDeactivatedLampsHoursAtTop());
            Assertions.assertEquals(3, displayDataHours.activatedLampsHoursAtBottom());
            Assertions.assertEquals(1, displayDataHours.getDeactivatedLampsHoursAtBottom());
      }

      @Test
      public void getLampsWhenTopHas4ActivatedAndBottomHas4Activated() {
            var displayDataHours = new DisplayDataHours(4, 4);

            Assertions.assertEquals(4, displayDataHours.activatedLampsHoursAtTop());
            Assertions.assertEquals(0, displayDataHours.getDeactivatedLampsHoursAtTop());
            Assertions.assertEquals(4, displayDataHours.activatedLampsHoursAtBottom());
            Assertions.assertEquals(0, displayDataHours.getDeactivatedLampsHoursAtBottom());
      }

      @Test
      public void getLampsWhenTopHas0ActivatedAndBottomHas0Activated() {
            var displayDataHours = new DisplayDataHours(0, 0);

            Assertions.assertEquals(0, displayDataHours.activatedLampsHoursAtTop());
            Assertions.assertEquals(4, displayDataHours.getDeactivatedLampsHoursAtTop());
            Assertions.assertEquals(0, displayDataHours.activatedLampsHoursAtBottom());
            Assertions.assertEquals(4, displayDataHours.getDeactivatedLampsHoursAtBottom());
      }

      @Test
      public void shouldThrowExceptionWhenNumberOfLampsIsImproper() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new DisplayDataHours(11, 1));
            Assertions.assertThrows(IllegalArgumentException.class, () -> new DisplayDataHours(1, 7));
      }
}