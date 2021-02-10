package kisonar.poc.learning.java.jdk.string;

import org.junit.jupiter.api.BeforeEach;

public class StringMethodsTest {

    private StringMethods stringMethods;
    private String something = "something ";
    private String anything = " anything ";
    private String nothing = "    ";
    private String whatecer = " whatever anymehwre   ";


    @BeforeEach
    public void setUp() {
        stringMethods = new StringMethods();
    }
}
