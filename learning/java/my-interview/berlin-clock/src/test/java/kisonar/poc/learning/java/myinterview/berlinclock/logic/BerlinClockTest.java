package kisonar.poc.learning.java.myinterview.berlinclock.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        Result result = berlinClock.convert(time);
        Assertions.assertEquals(16, result.hours);
        Assertions.assertEquals(50, result.minutes);

    }

    @Test
    public void logicFlow() {
        Result result = berlinClock.convert("17:11");


    }


    @Test
    void print() {
    }





}