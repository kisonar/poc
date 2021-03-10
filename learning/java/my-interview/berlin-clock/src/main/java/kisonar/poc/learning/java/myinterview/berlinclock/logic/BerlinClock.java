package kisonar.poc.learning.java.myinterview.berlinclock.logic;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class BerlinClock {

    private Converter converter;

    public BerlinClock() {
        converter = new Converter();
    }


    public Result convert(String inputHhMm) {
        LocalTime dateTime = LocalTime.parse(inputHhMm);
        return converter.convertToBerlinClockFormat(dateTime);
    }

    public void printResult(Result result) {
    }


}
