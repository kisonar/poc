package mossad.java.features.base.mossad.java.features.base.math;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BigDecimalTest {

    @Test
    public void basic() {
        BigDecimal bdOne = new BigDecimal("1.4523434");
        BigDecimal fractionalPart = bdOne.remainder(BigDecimal.ONE); // Result:  0.4523434
        int fractionalIntValue = fractionalPart.intValue();
        Assertions.assertEquals(0, fractionalIntValue);
        int bsIntValue = bdOne.intValue();
        Assertions.assertEquals(1, bsIntValue);
        Assertions.assertEquals(Double.parseDouble("0.4523434"), fractionalPart.doubleValue());

        //======================================
        BigDecimal bdTwo = new BigDecimal("0.4523434");
        fractionalPart = bdOne.remainder(BigDecimal.ONE); // Result:  0.4523434
        fractionalIntValue = fractionalPart.intValue();
        Assertions.assertEquals(0, fractionalIntValue);
        bsIntValue = bdTwo.intValue();
        Assertions.assertEquals(0, bsIntValue);

        BigDecimal bdThree = new BigDecimal("3.0");
        fractionalPart = bdThree.remainder(BigDecimal.ONE); // Result:  0.4523434
        fractionalIntValue = fractionalPart.intValue();
        Assertions.assertEquals(0, fractionalIntValue);
        bsIntValue = bdThree.intValue();
        Assertions.assertEquals(3, bsIntValue);
    }
}