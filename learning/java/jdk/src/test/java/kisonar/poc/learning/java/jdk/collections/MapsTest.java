package kisonar.poc.learning.java.jdk.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static kisonar.poc.learning.java.jdk.collections.CollectionsFactory.getEntityHashNokEqualsOk;
import static kisonar.poc.learning.java.jdk.collections.CollectionsFactory.getEntityHashOkEqualsNok;
import static kisonar.poc.learning.java.jdk.collections.CollectionsFactory.getEntityOK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapsTest {

    private final Map<Entity, String> map = new HashMap<>();

    private final String name1 = "one";
    private final String name2 = "two";
    private final String name3 = "three";
    private final String name4 = "four";
    private final String name5 = "five";
    private final String value1 = "value1";
    private final String value2 = "value2";
    private final String value3 = "value3";
    private final String value4 = "value4";
    private final String value5 = "value5";
    private final int id1 = 1;
    private final int id2 = 2;
    private final int id3 = 3;
    private final int id4 = 4;
    private final int id5 = 5;

    @BeforeEach
    public void setUp() {
        map.clear();
    }

    @Test
    public void hashOkEqualsOkWithDifferentKeys() {
        Entity entity1 = getEntityOK(id1, name1, value1);
        Entity entity2 = getEntityOK(id2, name2, value2);
        Entity entity3 = getEntityOK(id3, name3, value3);
        Entity entity4 = getEntityOK(id4, name4, value4);
        Entity entity5 = getEntityOK(id5, name5, value5);

        map.put(entity1, value1);
        map.put(entity2, value2);
        map.put(entity3, value3);
        map.put(entity4, value4);
        map.put(entity5, value5);

        assertEquals(5, map.size());

        assertSame(value1, map.get(entity1));
        assertSame(value1, map.get(entity1));
        assertSame(value1, map.get(entity1));
        assertSame(value1, map.get(entity1));
        assertSame(value1, map.get(entity1));


        assertTrue(map.containsKey(entity1));
        assertTrue(map.containsKey(entity2));
        assertTrue(map.containsKey(entity3));
        assertTrue(map.containsKey(entity4));
        assertTrue(map.containsKey(entity5));
        assertTrue(map.containsValue(value1));
        assertTrue(map.containsValue(value2));
        assertTrue(map.containsValue(value3));
        assertTrue(map.containsValue(value4));
        assertTrue(map.containsValue(value5));

    }

    @Test
    public void hashOkEqualsOkWithSameKeys() {
        Entity entity1 = getEntityOK(id1, name1, value1);
        Entity entity2 = getEntityOK(id2, name2, value2);
        Entity entity3 = getEntityOK(id3, name3, value3);
        Entity entity4 = getEntityOK(id3, name3, value3);
        Entity entity5 = getEntityOK(id3, name3, value5);

        map.put(entity1, value1);
        map.put(entity2, value2);
        map.put(entity3, value3);
        map.put(entity4, value4);
        map.put(entity5, value5);

        assertEquals(4, map.size());
        assertTrue(map.containsKey(entity1));
        assertTrue(map.containsKey(entity2));
        assertTrue(map.containsKey(entity3));
        assertTrue(map.containsKey(entity4));
        assertTrue(map.containsKey(entity5));

        assertTrue(map.containsValue(value1));
        assertTrue(map.containsValue(value2));
        assertFalse(map.containsValue(value3)); //3 overridden by 4 as keys for 3 and 4 have same hashId and equals
        assertTrue(map.containsValue(value4));
        assertTrue(map.containsValue(value5));

    }

    @Test
    public void hashOkEqualsNokDifferentKeys() { //equals calculated with id and name only
        Entity entity1 = getEntityHashOkEqualsNok(id1, name1, value1);
        Entity entity2 = getEntityHashOkEqualsNok(id2, name2, value2);
        Entity entity3 = getEntityHashOkEqualsNok(id3, name3, value3);
        Entity entity4 = getEntityHashOkEqualsNok(id4, name4, value4);

        map.put(entity1, value1);
        map.put(entity2, value2);
        map.put(entity3, value3);
        map.put(entity4, value4);

        assertEquals(4, map.size());
        assertTrue(map.containsKey(entity1));
        assertTrue(map.containsKey(entity2));
        assertTrue(map.containsKey(entity3));
        assertTrue(map.containsKey(entity4));
        assertTrue(map.containsValue(value1));
        assertTrue(map.containsValue(value2));
        assertTrue(map.containsValue(value3));
        assertTrue(map.containsValue(value4));
    }

    @Test
    public void hashOkEqualsNokSameKeys() { //equals calculated with id and name only
        Entity entity1 = getEntityHashOkEqualsNok(id1, name1, value1);
        Entity entity2 = getEntityHashOkEqualsNok(id2, name2, value2);
        Entity entity3 = getEntityHashOkEqualsNok(id3, name3, value3);
        Entity entity4 = getEntityHashOkEqualsNok(id3, name3, value4);
        Entity entity5 = getEntityHashOkEqualsNok(id3, name3, value3);

        map.put(entity1, value1);
        map.put(entity2, value2);
        map.put(entity3, value3);
        map.put(entity4, value4);

        assertEquals(4, map.size());
        assertTrue(map.containsKey(entity1));
        assertTrue(map.containsKey(entity2));
        assertTrue(map.containsKey(entity3));
        assertTrue(map.containsKey(entity4));
        assertTrue(map.containsValue(value1));
        assertTrue(map.containsValue(value2));
        assertTrue(map.containsValue(value3));
        assertTrue(map.containsValue(value4));

        assertSame(value3, map.get(entity3));
        assertSame(value4, map.get(entity4));

    }

    @Test
    public void hashNokEqualsOkDifferentKeys() {
        Entity entity1 = getEntityHashNokEqualsOk(id1, name1, value1);
        Entity entity2 = getEntityHashNokEqualsOk(id2, name2, value2);
        Entity entity3 = getEntityHashNokEqualsOk(id3, name3, value3);
        Entity entity4 = getEntityHashNokEqualsOk(id4, name4, value4);

        map.put(entity1, value1);
        map.put(entity2, value2);
        map.put(entity3, value3);
        map.put(entity4, value4);

        assertEquals(4, map.size());
        assertTrue(map.containsKey(entity1));
        assertTrue(map.containsKey(entity2));
        assertTrue(map.containsKey(entity3));
        assertTrue(map.containsKey(entity4));
        assertTrue(map.containsValue(value1));
        assertTrue(map.containsValue(value2));
        assertTrue(map.containsValue(value3));
        assertTrue(map.containsValue(value4));
    }

    @Test
    public void hashNokEqualsOkSameKeys() {
        Entity entity1 = getEntityHashNokEqualsOk(id1, name1, value1);
        Entity entity2 = getEntityHashNokEqualsOk(id2, name2, value2);
        Entity entity3 = getEntityHashNokEqualsOk(id3, name3, value3);
        Entity entity4 = getEntityHashNokEqualsOk(id3, name3, value4);

        map.put(entity1, value1);
        map.put(entity2, value2);
        map.put(entity3, value3);
        map.put(entity4, value4);

        assertEquals(4, map.size());
        assertTrue(map.containsKey(entity1));
        assertTrue(map.containsKey(entity2));
        assertTrue(map.containsKey(entity3));
        assertTrue(map.containsKey(entity4));
        assertTrue(map.containsValue(value1));
        assertTrue(map.containsValue(value2));
        assertTrue(map.containsValue(value3));
        assertTrue(map.containsValue(value4));
    }

}