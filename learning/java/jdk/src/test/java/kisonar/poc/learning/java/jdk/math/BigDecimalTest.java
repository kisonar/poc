package kisonar.poc.learning.java.jdk.math;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BigDecimalTest {

      @Test
      public void basic() {
            BigDecimal bdOne = new BigDecimal("1.4523434");
            BigDecimal fractionalPart = bdOne.remainder(BigDecimal.ONE); // Result:  0.4523434
            int fractionalIntValue = fractionalPart.intValue();
            assertEquals(0, fractionalIntValue);
            int bsIntValue = bdOne.intValue();
            assertEquals(1, bsIntValue);
            assertEquals(Double.parseDouble("0.4523434"), fractionalPart.doubleValue());

            //======================================
            BigDecimal bdTwo = new BigDecimal("0.4523434");
            fractionalPart = bdOne.remainder(BigDecimal.ONE); // Result:  0.4523434
            fractionalIntValue = fractionalPart.intValue();
            assertEquals(0, fractionalIntValue);
            bsIntValue = bdTwo.intValue();
            assertEquals(0, bsIntValue);

            BigDecimal bdThree = new BigDecimal("3.0");
            fractionalPart = bdThree.remainder(BigDecimal.ONE); // Result:  0.4523434
            fractionalIntValue = fractionalPart.intValue();
            assertEquals(0, fractionalIntValue);
            bsIntValue = bdThree.intValue();
            assertEquals(3, bsIntValue);
      }
}