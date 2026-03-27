package kisonar.poc.learning.java.myinterview.berlinclock.logic.display;

public record DisplayDataMinutes(
        int activatedLampsMinutesAtTop,
        int activatedLampsMinutesAtBottom

) {
      private final static int TOTAL_NUMBER_OF_LAMPS_AT_TOP = 11;
      private final static int TOTAL_NUMBER_OF_LAMPS_AT_BOTTOM = 4;

      public DisplayDataMinutes {
            if (activatedLampsMinutesAtTop < 0 || activatedLampsMinutesAtTop > TOTAL_NUMBER_OF_LAMPS_AT_TOP) {
                  throw new IllegalArgumentException("Activated lamps at top must be between 0 and " + TOTAL_NUMBER_OF_LAMPS_AT_TOP);
            }
            if (activatedLampsMinutesAtBottom < 0 || activatedLampsMinutesAtBottom > TOTAL_NUMBER_OF_LAMPS_AT_BOTTOM) {
                  throw new IllegalArgumentException("Activated lamps at bottom must be between 0 and " + TOTAL_NUMBER_OF_LAMPS_AT_BOTTOM);
            }
      }

      public int getDeactivatedLampsMinutesAtTop() {
            return TOTAL_NUMBER_OF_LAMPS_AT_TOP - activatedLampsMinutesAtTop;
      }

      public int getDeactivatedLampsMinutesAtBottom() {
            return TOTAL_NUMBER_OF_LAMPS_AT_BOTTOM - activatedLampsMinutesAtBottom;
      }
}
