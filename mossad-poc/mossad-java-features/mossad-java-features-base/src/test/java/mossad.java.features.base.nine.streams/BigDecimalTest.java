package mossad.java.features.base.nine.streams;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BigDecimalTest {


    @Test
    public void basic(){

        BigDecimal bdOne = new BigDecimal( "1.4523434" );
        BigDecimal fractionalPart = bdOne.remainder( BigDecimal.ONE ); // Result:  0.4523434

        Assertions.assertNotNull(fractionalPart);
        int fractional = fractionalPart.intValue();
        Assertions.assertEquals(0,fractional);
        int value  = bdOne.intValue();
        Assertions.assertEquals(1,value);
        Assertions.assertEquals(Double.parseDouble("0.4523434"),fractionalPart.doubleValue());


        //======================================
        BigDecimal bdTwo = new BigDecimal( "0.4523434" );
        fractionalPart = bdOne.remainder( BigDecimal.ONE ); // Result:  0.4523434

        Assertions.assertNotNull(fractionalPart);
        fractional = fractionalPart.intValue();
        Assertions.assertEquals(0,fractional);
        value  = bdTwo.intValue();
        Assertions.assertEquals(0,value);


        BigDecimal bdThree = new BigDecimal( "3.0" );
        fractionalPart = bdThree.remainder( BigDecimal.ONE ); // Result:  0.4523434

        Assertions.assertNotNull(fractionalPart);
        fractional = fractionalPart.intValue();
        Assertions.assertEquals(0,fractional);
        value  = bdThree.intValue();
        Assertions.assertEquals(3,value);




    }

}
