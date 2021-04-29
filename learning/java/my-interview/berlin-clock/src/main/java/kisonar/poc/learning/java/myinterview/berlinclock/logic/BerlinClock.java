package kisonar.poc.learning.java.myinterview.berlinclock.logic;

import kisonar.poc.learning.java.myinterview.berlinclock.logic.converter.Converter;
import kisonar.poc.learning.java.myinterview.berlinclock.logic.converter.ConvertionResult;

import java.time.LocalTime;

public class BerlinClock {

    private Converter converter;

    public BerlinClock() {
        converter = new Converter();
    }

  public ConvertionResult convert(String inputHhMm) {
    LocalTime dateTime = LocalTime.parse(inputHhMm);
    return converter.convertToBerlinClockFormat(dateTime);
  }

  public void printResult(ConvertionResult convertionResult) {
  }
}