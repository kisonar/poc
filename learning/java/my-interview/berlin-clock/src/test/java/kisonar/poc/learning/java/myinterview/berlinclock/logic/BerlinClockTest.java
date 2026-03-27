package kisonar.poc.learning.java.myinterview.berlinclock.logic;

import org.junit.jupiter.api.AfterEach;
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
      void show() {
            String time = "16:50";
            berlinClock.run(time);
      }

      @Test
      public void logicFlow() {
            //ConvertionResult convertionResult = berlinClock.convert("17:11");

      }


      @Test
      void print() {
      }

}