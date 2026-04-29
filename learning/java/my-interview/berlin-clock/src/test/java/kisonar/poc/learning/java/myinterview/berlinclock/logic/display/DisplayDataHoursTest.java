package kisonar.poc.learning.java.myinterview.berlinclock.logic.display;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DisplayDataHoursTest {

      @Test
      public void getLampsWhenTopHas3ActivatedAndBottomHas1Activated() {
            var displayDataHours = new DisplayDataHours(3, 1);

            Assertions.assertEquals(3, displayDataHours.activatedLampsHoursAtTop());
            Assertions.assertEquals(1, displayDataHours.getDeactivatedLampsHoursAtTop());
            Assertions.assertEquals(1, displayDataHours.activatedLampsHoursAtBottom());
            Assertions.assertEquals(3, displayDataHours.getDeactivatedLampsHoursAtBottom());
      }

      @Test
      public void getLampsWhenTopHas4ActivatedAndBottomHas0Activated() {
            var displayDataHours = new DisplayDataHours(4, 0);

            Assertions.assertEquals(4, displayDataHours.activatedLampsHoursAtTop());
            Assertions.assertEquals(0, displayDataHours.getDeactivatedLampsHoursAtTop());
            Assertions.assertEquals(0, displayDataHours.activatedLampsHoursAtBottom());
            Assertions.assertEquals(4, displayDataHours.getDeactivatedLampsHoursAtBottom());
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
            Assertions.assertThrows(IllegalArgumentException.class, () -> new DisplayDataHours(5, 1));
            Assertions.assertThrows(IllegalArgumentException.class, () -> new DisplayDataHours(1, 5));
            Assertions.assertThrows(IllegalArgumentException.class, () -> new DisplayDataHours(-1, 1));
            Assertions.assertThrows(IllegalArgumentException.class, () -> new DisplayDataHours(1, -1));
      }
}