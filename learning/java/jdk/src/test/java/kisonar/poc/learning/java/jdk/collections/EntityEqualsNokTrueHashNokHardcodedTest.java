package kisonar.poc.learning.java.jdk.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static kisonar.poc.learning.java.jdk.collections.CollectionsFactory.getEntityEqualsNokTrueHashNokHardcoded;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityEqualsNokTrueHashNokHardcodedTest extends CollectionsSetup {

      @BeforeEach
      public void setUp() {
            map.clear();
      }

      @Test
      public void equalsOkHashNokWithDifferentKeys() {
            Entity entityKey1 = getEntityEqualsNokTrueHashNokHardcoded(id1, name1, value1);
            Entity entityKey2 = getEntityEqualsNokTrueHashNokHardcoded(id2, name2, value2);
            Entity entityKey3 = getEntityEqualsNokTrueHashNokHardcoded(id3, name3, value3);
            Entity entityKey4 = getEntityEqualsNokTrueHashNokHardcoded(id4, name4, value4);
            Entity entityKey5 = getEntityEqualsNokTrueHashNokHardcoded(id5, name5, value5);
            Entity entityKey6 = getEntityEqualsNokTrueHashNokHardcoded(id6, name6, value6);
            map.put(entityKey1, value1);
            map.put(entityKey2, value2);
            map.put(entityKey3, value3);
            map.put(entityKey4, value4);
            map.put(entityKey5, value5);
            map.put(entityKey6, value6);

            assertEquals(1, map.size());

            assertTrue(map.containsKey(entityKey1));
            assertTrue(map.containsKey(entityKey2));
            assertTrue(map.containsKey(entityKey3));
            assertTrue(map.containsKey(entityKey4));
            assertTrue(map.containsKey(entityKey5));

            assertFalse(map.containsValue(value1));
            assertFalse(map.containsValue(value2));
            assertFalse(map.containsValue(value3));
            assertFalse(map.containsValue(value4));
            assertFalse(map.containsValue(value5));
            assertTrue(map.containsValue(value6));

            assertNotSame(value1, map.get(entityKey1));
            assertNotSame(value2, map.get(entityKey2));
            assertNotSame(value3, map.get(entityKey3));
            assertNotSame(value4, map.get(entityKey4));
            assertNotSame(value5, map.get(entityKey5));
            assertSame(value6, map.get(entityKey6));
      }

      @Test
      public void equalsOkHashNokWithSameKeys() {
            Entity entityKey1 = getEntityEqualsNokTrueHashNokHardcoded(id1, name1, value1);
            Entity entityKey2 = getEntityEqualsNokTrueHashNokHardcoded(id2, name2, value2);
            Entity entityKey3 = getEntityEqualsNokTrueHashNokHardcoded(id3, name3, value3);
            Entity entityKey4 = getEntityEqualsNokTrueHashNokHardcoded(id4, name3, value3);
            Entity entityKey5 = getEntityEqualsNokTrueHashNokHardcoded(id5, name5, value5);
            Entity entityKey6 = getEntityEqualsNokTrueHashNokHardcoded(id6, name5, value5);
            map.put(entityKey1, value1);
            map.put(entityKey2, value2);
            map.put(entityKey3, value3);
            map.put(entityKey4, value4);
            map.put(entityKey5, value5);
            map.put(entityKey6, value6);

            assertEquals(1, map.size());

            assertTrue(map.containsKey(entityKey1));
            assertTrue(map.containsKey(entityKey2));
            assertTrue(map.containsKey(entityKey3));
            assertTrue(map.containsKey(entityKey4));
            assertTrue(map.containsKey(entityKey5));
            assertTrue(map.containsKey(entityKey6));

            assertFalse(map.containsValue(value1));
            assertFalse(map.containsValue(value2));
            assertFalse(map.containsValue(value3));
            assertFalse(map.containsValue(value4));
            assertFalse(map.containsValue(value5));
            assertTrue(map.containsValue(value6));

            assertNotSame(value1, map.get(entityKey1));
            assertNotSame(value2, map.get(entityKey2));
            assertNotSame(value3, map.get(entityKey3));
            assertNotSame(value4, map.get(entityKey4));
            assertNotSame(value5, map.get(entityKey5));
            assertSame(value6, map.get(entityKey6));
      }

}