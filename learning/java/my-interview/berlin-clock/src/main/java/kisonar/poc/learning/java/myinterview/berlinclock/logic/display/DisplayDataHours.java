package kisonar.poc.learning.java.myinterview.berlinclock.logic.display;

public record DisplayDataHours(
        int activatedLampsHoursAtTop,
        int activatedLampsHoursAtBottom
) {
      private final static int TOTAL_NUMBER_OF_LAMPS_AT_TOP = 4;
      private final static int TOTAL_NUMBER_OF_LAMPS_AT_BOTTOM = 4;

      public DisplayDataHours {
            if (activatedLampsHoursAtTop < 0 || activatedLampsHoursAtTop > TOTAL_NUMBER_OF_LAMPS_AT_TOP) {
                  throw new IllegalArgumentException("Activated lamps at top must be between 0 and " + TOTAL_NUMBER_OF_LAMPS_AT_TOP);
            }
            if (activatedLampsHoursAtBottom < 0 || activatedLampsHoursAtBottom > TOTAL_NUMBER_OF_LAMPS_AT_BOTTOM) {
                  throw new IllegalArgumentException("Activated lamps at bottom must be between 0 and " + TOTAL_NUMBER_OF_LAMPS_AT_BOTTOM);
            }
      }

      public int getDeactivatedLampsHoursAtTop() {
            return TOTAL_NUMBER_OF_LAMPS_AT_TOP - activatedLampsHoursAtTop;
      }

      public int getDeactivatedLampsHoursAtBottom() {
            return TOTAL_NUMBER_OF_LAMPS_AT_BOTTOM - activatedLampsHoursAtBottom;
      }
}
