package kisonar.poc.learning.java.jdk.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static kisonar.poc.learning.java.jdk.collections.CollectionsFactory.getEntityEqualsOkHashOk;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityEqualsOkHashOkTest {

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
      public void equalsOkHashOkWithDifferentKeys() {
            Entity entityKey1 = getEntityEqualsOkHashOk(id1, name1, value1);
            Entity entityKey2 = getEntityEqualsOkHashOk(id2, name2, value2);
            Entity entityKey3 = getEntityEqualsOkHashOk(id3, name3, value3);
            Entity entityKey4 = getEntityEqualsOkHashOk(id4, name4, value4);
            Entity entityKey5 = getEntityEqualsOkHashOk(id5, name5, value5);

            map.put(entityKey1, value1);
            map.put(entityKey2, value2);
            map.put(entityKey3, value3);
            map.put(entityKey4, value4);
            map.put(entityKey5, value5);

            assertEquals(5, map.size());

            assertTrue(map.containsKey(entityKey1));
            assertTrue(map.containsKey(entityKey2));
            assertTrue(map.containsKey(entityKey3));
            assertTrue(map.containsKey(entityKey4));
            assertTrue(map.containsKey(entityKey5));
            assertTrue(map.containsValue(value1));
            assertTrue(map.containsValue(value2));
            assertTrue(map.containsValue(value3));
            assertTrue(map.containsValue(value4));
            assertTrue(map.containsValue(value5));

            assertSame(value1, map.get(entityKey1));
            assertSame(value2, map.get(entityKey2));
            assertSame(value3, map.get(entityKey3));
            assertSame(value4, map.get(entityKey4));
            assertSame(value5, map.get(entityKey5));
      }

      @Test
      public void equalsOkHashOkWithSameKeys() {
            Entity entityKey1 = getEntityEqualsOkHashOk(id1, name1, value1);
            Entity entityKey2 = getEntityEqualsOkHashOk(id2, name2, value2);
            Entity entityKey3 = getEntityEqualsOkHashOk(id3, name3, value3);
            Entity entityKey4 = getEntityEqualsOkHashOk(id3, name3, value3); // same as key three
            Entity entityKey5 = getEntityEqualsOkHashOk(id3, name3, value5);

            map.put(entityKey1, value1);
            map.put(entityKey2, value2);
            map.put(entityKey3, value3);
            map.put(entityKey4, value4);
            map.put(entityKey5, value5);

            assertEquals(4, map.size());

            assertTrue(map.containsKey(entityKey1));
            assertTrue(map.containsKey(entityKey2));
            assertTrue(map.containsKey(entityKey3));
            assertTrue(map.containsKey(entityKey4));
            assertTrue(map.containsKey(entityKey5));

            assertTrue(map.containsValue(value1));
            assertTrue(map.containsValue(value2));
            assertFalse(map.containsValue(value3)); //overridden by 4
            assertTrue(map.containsValue(value4));
            assertTrue(map.containsValue(value5));

            assertSame(value1, map.get(entityKey1));
            assertSame(value2, map.get(entityKey2));
            assertSame(value4, map.get(entityKey3)); //overridden by  4
            assertSame(value4, map.get(entityKey4));
            assertSame(value5, map.get(entityKey5));

      }

}