package kisonar.poc.learning.java.myinterview.berlinclock.logic.converter;

import java.time.LocalTime;

public class Converter {

    public Converter() {
    }

  public ConvertionResult convertToBerlinClockFormat(LocalTime time) {
    int hoursRangeFive = time.getHour() / 5;
    int hoursRangeOne = time.getHour() % 5;
    int minutesRangeFive = time.getMinute() / 5;
    int minutesRangeOne = time.getMinute() % 5;
    return new ConvertionResult(time.getHour(), time.getMinute(), hoursRangeFive, hoursRangeOne,
        minutesRangeFive, minutesRangeOne);
  }
}