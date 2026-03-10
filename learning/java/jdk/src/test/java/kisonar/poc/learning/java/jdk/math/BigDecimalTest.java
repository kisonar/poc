package kisonar.poc.learning.java.jdk.math;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BigDecimalTest {

      @Test
      public void extractParts1dot4523434() {
            BigDecimal bdOne = new BigDecimal("1.4523434");

            BigDecimal fractionalPart = bdOne.remainder(BigDecimal.ONE);
            int bsIntValue = bdOne.intValue();

            assertEquals(0, fractionalPart.intValue());
            assertEquals(1, bsIntValue);
            assertEquals(Double.parseDouble("0.4523434"), fractionalPart.doubleValue());
      }

      @Test
      public void extractParts0dot4523434() {
            BigDecimal bdTwo = new BigDecimal("0.4523434");

            int fractionalPart = bdTwo.remainder(BigDecimal.ONE).intValue();
            int bsIntValue = bdTwo.intValue();

            assertEquals(0, fractionalPart);
            assertEquals(0, bsIntValue);
      }

      @Test
      public void extractParts3dot0() {
            BigDecimal bdThree = new BigDecimal("3.0");

            int fractionalPart = bdThree.remainder(BigDecimal.ONE).intValue();
            int bsIntValue = bdThree.intValue();

            assertEquals(0, fractionalPart);
            assertEquals(3, bsIntValue);
      }
}