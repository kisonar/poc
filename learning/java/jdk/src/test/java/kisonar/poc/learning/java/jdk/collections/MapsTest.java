package kisonar.poc.learning.java.jdk.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MapsTest {

    private Map<EntityHashOkEqualsOk, String> map = new HashMap<>();
    private String itemName1 = "one";
    private String itemName2 = "one";
    private String itemName3 = "one";

    @BeforeEach
    public void setUp() {
        map.clear();
    }

    @Test
    public void hashMapHashOKEqualsOK() {
        EntityHashOkEqualsOk item1 = new EntityHashOkEqualsOk(1, itemName1, "some");
        EntityHashOkEqualsOk item2 = new EntityHashOkEqualsOk(2, itemName2, "some");
        EntityHashOkEqualsOk item3 = new EntityHashOkEqualsOk(3, itemName3, "some");
        map.putIfAbsent(item1, itemName1);
        map.putIfAbsent(item2, itemName2);
        map.putIfAbsent(item3, itemName3);

        Assertions.assertEquals(3, map.size());
    }


}
