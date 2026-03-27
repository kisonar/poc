package kisonar.poc.learning.java.myinterview.berlinclock.logic.display;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DisplayDataMinutesTest {

      @Test
      public void getLampsWhenTopHas8ActivatedAndBottomHas4Activated() {
            var displayDataMinutes = new DisplayDataMinutes(8, 4);

            Assertions.assertEquals(8, displayDataMinutes.activatedLampsMinutesAtTop());
            Assertions.assertEquals(3, displayDataMinutes.getDeactivatedLampsMinutesAtTop());
            Assertions.assertEquals(4, displayDataMinutes.activatedLampsMinutesAtBottom());
            Assertions.assertEquals(0, displayDataMinutes.getDeactivatedLampsMinutesAtBottom());
      }

      @Test
      public void getLampsWhenTopHas11ActivatedAndBottomHas0Activated() {
            var displayDataMinutes = new DisplayDataMinutes(11, 0);

            Assertions.assertEquals(11, displayDataMinutes.activatedLampsMinutesAtTop());
            Assertions.assertEquals(0, displayDataMinutes.getDeactivatedLampsMinutesAtTop());
            Assertions.assertEquals(0, displayDataMinutes.activatedLampsMinutesAtBottom());
            Assertions.assertEquals(4, displayDataMinutes.getDeactivatedLampsMinutesAtBottom());
      }

      @Test
      public void getLampsWhenTopHas0ActivatedAndBottomHas0Activated() {
            var displayDataMinutes = new DisplayDataMinutes(0, 0);

            Assertions.assertEquals(0, displayDataMinutes.activatedLampsMinutesAtTop());
            Assertions.assertEquals(11, displayDataMinutes.getDeactivatedLampsMinutesAtTop());
            Assertions.assertEquals(0, displayDataMinutes.activatedLampsMinutesAtBottom());
            Assertions.assertEquals(4, displayDataMinutes.getDeactivatedLampsMinutesAtBottom());
      }

      @Test
      public void shouldThrowExceptionWhenNumberOfLampsIsImproper() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new DisplayDataMinutes(12, 1));
            Assertions.assertThrows(IllegalArgumentException.class, () -> new DisplayDataMinutes(1, 5));
            Assertions.assertThrows(IllegalArgumentException.class, () -> new DisplayDataMinutes(-1, 1));
            Assertions.assertThrows(IllegalArgumentException.class, () -> new DisplayDataMinutes(1, -1));
      }
}
