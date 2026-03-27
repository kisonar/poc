package kisonar.poc.learning.java.myinterview.berlinclock.logic.converter;

import kisonar.poc.learning.java.myinterview.berlinclock.logic.display.DisplayData;
import kisonar.poc.learning.java.myinterview.berlinclock.logic.display.DisplayDataHours;
import kisonar.poc.learning.java.myinterview.berlinclock.logic.display.DisplayDataMinutes;

import java.time.LocalTime;

public class Converter {

      public Converter() {
      }

      public DisplayData transformLocalTimeToDisplayData(LocalTime time) {
            int hoursRangeFive = time.getHour() / 5;
            int hoursRangeOne = time.getHour() % 5;
            int minutesRangeFive = time.getMinute() / 5;
            int minutesRangeOne = time.getMinute() % 5;
            var displayDataHours = new DisplayDataHours(hoursRangeFive, hoursRangeOne);
            var displayDataMinutes = new DisplayDataMinutes(minutesRangeFive, minutesRangeOne);
            return new DisplayData(time, displayDataHours, displayDataMinutes);
      }
}