package kisonar.poc.learning.java.myinterview.berlinclock.logic;

public class Result {

    final int hoursRangeFive;
    final int hoursRangeOne;
    final int minutesRangeFive;
    final int minutesRangeOne;
    int hours;
    int minutes;

    public Result(int hoursRangeFive, int hoursRangeOne, int minutesRangeFive, int minutesRangeOne) {
        this.hoursRangeFive = hoursRangeFive;
        this.hoursRangeOne = hoursRangeOne;
        this.minutesRangeFive = minutesRangeFive;
        this.minutesRangeOne = minutesRangeOne;
    }

    Result(int hours, int minutes, int hoursRangeFive, int hoursRangeOne, int minutesRangeFive, int minutesRangeOne) {
        this(hoursRangeFive, hoursRangeOne, minutesRangeFive, minutesRangeOne);
        this.hours = hours;
        this.minutes = minutes;

    }

}
