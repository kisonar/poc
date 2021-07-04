package kisonar.poc.learning.java.myinterview.berlinclock.logic;

import kisonar.poc.learning.java.myinterview.berlinclock.logic.converter.ConvertionResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BerlinClockTest {

    private BerlinClock berlinClock;

    @BeforeEach
    void setUp() {
        berlinClock = new BerlinClock();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void convert() {
      String time = "16:50";
      ConvertionResult convertionResult = berlinClock.convert(time);
      Assertions.assertEquals(16, convertionResult.hours);
      Assertions.assertEquals(50, convertionResult.minutes);

    }

    @Test
    public void logicFlow() {
      ConvertionResult convertionResult = berlinClock.convert("17:11");

    }


    @Test
    void print() {
    }





}