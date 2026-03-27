package kisonar.poc.learning.java.myinterview.berlinclock.logic;

import kisonar.poc.learning.java.myinterview.berlinclock.logic.converter.Converter;

import java.time.LocalTime;

public class BerlinClock {

      private final Converter converter;

      public BerlinClock() {
            converter = new Converter();
      }

      public void run(String inputHhMm) {
            LocalTime dateTime = LocalTime.parse(inputHhMm);
            var displayData = converter.transformLocalTimeToDisplayData(dateTime);
            displayData.print();
      }
}