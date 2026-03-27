package kisonar.poc.learning.java.myinterview.berlinclock.logic.display;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;

public class DisplayDataTest {

      private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      private PrintStream originalSystemOut;

      @BeforeEach
      void setUp() {
            originalSystemOut = System.out;
            System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
      }

      @AfterEach
      void tearDown() {
            System.setOut(originalSystemOut);
      }

      @Test
      void shouldPrintDisplayDataForMixedLampConfiguration() {
            var displayData = new DisplayData(
                    LocalTime.of(12, 43),
                    new DisplayDataHours(2, 2),
                    new DisplayDataMinutes(8, 3)
            );

            displayData.print();

            var expectedOutput = String.join(System.lineSeparator(),
                    "RED RED BLACK BLACK ",
                    "RED RED BLACK BLACK ",
                    "RED RED RED RED RED RED RED RED BLACK BLACK BLACK ",
                    "RED RED RED BLACK ",
                    ""
            );

            Assertions.assertEquals(expectedOutput, outputStream.toString(StandardCharsets.UTF_8));
      }

      @Test
      void shouldPrintDisplayDataWhenAllLampsAreDeactivated() {
            var displayData = new DisplayData(
                    LocalTime.MIDNIGHT,
                    new DisplayDataHours(0, 0),
                    new DisplayDataMinutes(0, 0)
            );

            displayData.print();

            var expectedOutput = String.join(System.lineSeparator(),
                    "BLACK BLACK BLACK BLACK ",
                    "BLACK BLACK BLACK BLACK ",
                    "BLACK BLACK BLACK BLACK BLACK BLACK BLACK BLACK BLACK BLACK BLACK ",
                    "BLACK BLACK BLACK BLACK ",
                    ""
            );

            Assertions.assertEquals(expectedOutput, outputStream.toString(StandardCharsets.UTF_8));
      }
}
