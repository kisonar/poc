package kisonar.poc.learning.java.myinterview.berlinclock.logic.converter;

public class ConvertionResult {

  public final int hoursRangeFive;
  public final int hoursRangeOne;
  public final int minutesRangeFive;
  public final int minutesRangeOne;
  public int hours;
  public int minutes;

  public ConvertionResult(int hoursRangeFive, int hoursRangeOne, int minutesRangeFive,
      int minutesRangeOne) {
    this.hoursRangeFive = hoursRangeFive;
    this.hoursRangeOne = hoursRangeOne;
    this.minutesRangeFive = minutesRangeFive;
    this.minutesRangeOne = minutesRangeOne;
  }

  ConvertionResult(int hours, int minutes, int hoursRangeFive, int hoursRangeOne,
      int minutesRangeFive, int minutesRangeOne) {
    this(hoursRangeFive, hoursRangeOne, minutesRangeFive, minutesRangeOne);
    this.hours = hours;
    this.minutes = minutes;

  }

}
