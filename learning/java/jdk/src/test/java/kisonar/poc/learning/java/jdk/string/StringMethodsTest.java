package kisonar.poc.learning.java.jdk.string;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringMethodsTest {

    private StringMethods stringMethods;
    private final String something = " something ";
    private final String anything = " anything ";
    private final String nothing = "    ";
    private final String whatecer = " whatever anywhere   ";


    @BeforeEach
    public void setUp() {
        stringMethods = new StringMethods();
    }

    @Test
    public void join() {
        String result = String.join("|", something, anything);

        assertEquals(" something | anything ", result);
    }
}
