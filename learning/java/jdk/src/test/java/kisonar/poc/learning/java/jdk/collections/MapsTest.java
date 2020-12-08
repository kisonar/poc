package kisonar.poc.learning.java.jdk.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MapsTest {

    private Map<EntityHashNokEqualsNok, String> hashNokEqualsNok = new HashMap<>();
    private Map<EntityHashOkEqualsNok, String> hashNokEqualsOk = new HashMap<>();
    private Map<EntityHashOkEqualsNok, String> hashOkEqualsNok = new HashMap<>();
    private Map<EntityHashOkEqualsOk, String> hashOkEqualsOk = new HashMap<>();

    private String itemName1 = "one";
    private String itemName2 = "two";
    private String itemName3 = "three";
    private String value = "Sone";

    @BeforeEach
    public void setUp() {
        hashNokEqualsNok.clear();
        hashNokEqualsOk.clear();
        hashOkEqualsNok.clear();
        hashOkEqualsOk.clear();
    }

    @Test
    public void hashNokEqualsNok() {
        EntityHashNokEqualsNok item1 = new EntityHashNokEqualsNok(1, itemName1, value);
        EntityHashNokEqualsNok item2 = new EntityHashNokEqualsNok(2, itemName1, value);
        EntityHashNokEqualsNok item3 = new EntityHashNokEqualsNok(3, itemName1, value);

        hashNokEqualsNok.put(item1, itemName1);
        hashNokEqualsNok.put(item2, itemName2);
        hashNokEqualsNok.put(item3, itemName3);

        Assertions.assertEquals(3, hashOkEqualsOk.size());
    }

    @Test
    public void hashNokEqualsOk() {
        EntityHashOkEqualsNok item1 = new EntityHashOkEqualsNok(1, itemName1, value);
        EntityHashOkEqualsNok item2 = new EntityHashOkEqualsNok(2, itemName1, value);
        EntityHashOkEqualsNok item3 = new EntityHashOkEqualsNok(3, itemName1, value);

        hashNokEqualsOk.put(item1, itemName1);
        hashNokEqualsOk.put(item2, itemName2);
        hashNokEqualsOk.put(item3, itemName3);

        Assertions.assertEquals(3, hashOkEqualsOk.size());
    }

    @Test
    public void hashOkEqualsNok() {
        EntityHashOkEqualsNok item1 = new EntityHashOkEqualsNok(1, itemName1, value);
        EntityHashOkEqualsNok item2 = new EntityHashOkEqualsNok(2, itemName2, value);
        EntityHashOkEqualsNok item3 = new EntityHashOkEqualsNok(3, itemName3, value);

        hashOkEqualsNok.put(item1, itemName1);
        hashOkEqualsNok.put(item2, itemName2);
        hashOkEqualsNok.put(item3, itemName3);

        Assertions.assertEquals(3, hashOkEqualsOk.size());
    }

    @Test
    public void hashOkEqualsOk() {
        EntityHashOkEqualsOk item1 = new EntityHashOkEqualsOk(1, itemName1, value);
        EntityHashOkEqualsOk item2 = new EntityHashOkEqualsOk(2, itemName2, value);
        EntityHashOkEqualsOk item3 = new EntityHashOkEqualsOk(3, itemName3, value);

        hashOkEqualsOk.put(item1, itemName1);
        hashOkEqualsOk.put(item2, itemName2);
        hashOkEqualsOk.put(item3, itemName3);

        Assertions.assertEquals(3, hashOkEqualsOk.size());
    }

}
