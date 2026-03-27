package kisonar.poc.learning.java.myinterview.berlinclock.logic.display;

import java.time.LocalTime;

public record DisplayData(
        LocalTime localTime,
        DisplayDataHours displayDataHours,
        DisplayDataMinutes displayDataMinutes
) {
      public void print() {
            print(displayDataHours.activatedLampsHoursAtTop(), Color.RED);
            print(displayDataHours.getDeactivatedLampsHoursAtTop(), Color.BLACK);
            newLine();
            print(displayDataHours.activatedLampsHoursAtBottom(), Color.RED);
            print(displayDataHours.getDeactivatedLampsHoursAtBottom(), Color.BLACK);
            newLine();
            print(displayDataMinutes.activatedLampsMinutesAtTop(), Color.RED);
            print(displayDataMinutes.getDeactivatedLampsMinutesAtTop(), Color.BLACK);
            newLine();
            print(displayDataMinutes.activatedLampsMinutesAtBottom(), Color.RED);
            print(displayDataMinutes.getDeactivatedLampsMinutesAtBottom(), Color.BLACK);
            newLine();
      }

      private void print(int quantity, Color color) {
            for (int i = 0; i < quantity; i++) {
                  System.out.print(color + " ");
            }
      }

      private void newLine() {
            System.out.println();
      }
}