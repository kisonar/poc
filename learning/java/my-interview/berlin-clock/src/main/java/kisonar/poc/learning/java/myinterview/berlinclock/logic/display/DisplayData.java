package kisonar.poc.learning.java.myinterview.berlinclock.logic.display;

import java.time.LocalTime;

public record DisplayData(
        LocalTime localTime,
        DisplayDataHours displayDataHours,
        DisplayDataMinutes displayDataMinutes
) {
}