package kisonar.poc.learning.java.jdk.collections.record;

import kisonar.poc.learning.java.jdk.collections.CollectionsSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static kisonar.poc.learning.java.jdk.collections.CollectionsFactory.getRecord;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecordTest extends CollectionsSetup {

      private final Map<RecordEntity, String> map = new HashMap();

      @BeforeEach
      public void setUp() {
            this.map.clear();
      }

      @Test
      public void useRecordsWithUniqueValues() {
            RecordEntity entityKey1 = getRecord(id1, name1, value1);
            RecordEntity entityKey2 = getRecord(id2, name2, value2);
            RecordEntity entityKey3 = getRecord(id3, name3, value3);
            RecordEntity entityKey4 = getRecord(id4, name4, value4);
            RecordEntity entityKey5 = getRecord(id5, name5, value5);
            RecordEntity entityKey6 = getRecord(id6, name6, value6);
            map.put(entityKey1, value1);
            map.put(entityKey2, value2);
            map.put(entityKey3, value3);
            map.put(entityKey4, value4);
            map.put(entityKey5, value5);
            map.put(entityKey6, value6);

            assertEquals(6, map.size());

            assertTrue(map.containsKey(entityKey1));
            assertTrue(map.containsKey(entityKey2));
            assertTrue(map.containsKey(entityKey3));
            assertTrue(map.containsKey(entityKey4));
            assertTrue(map.containsKey(entityKey5));
            assertTrue(map.containsKey(entityKey6));
            assertTrue(map.containsValue(value1));
            assertTrue(map.containsValue(value2));
            assertTrue(map.containsValue(value3));
            assertTrue(map.containsValue(value4));
            assertTrue(map.containsValue(value5));
            assertTrue(map.containsValue(value6));

            assertSame(value1, map.get(entityKey1));
            assertSame(value2, map.get(entityKey2));
            assertSame(value3, map.get(entityKey3));
            assertSame(value4, map.get(entityKey4));
            assertSame(value5, map.get(entityKey5));
            assertSame(value6, map.get(entityKey6));
      }

      @Test
      public void useRecordsWithDuplicatedValues() {
            RecordEntity entityKey1 = getRecord(id1, name1, value1);
            RecordEntity entityKey2 = getRecord(id2, name2, value2);
            RecordEntity entityKey3 = getRecord(id3, name3, value3);
            RecordEntity entityKey4 = getRecord(id3, name3, value3);
            RecordEntity entityKey5 = getRecord(id3, name4, value5);
            RecordEntity entityKey6 = getRecord(id6, name5, value5);
            map.put(entityKey1, value1);
            map.put(entityKey2, value2);
            map.put(entityKey3, value3);
            map.put(entityKey4, value4);
            map.put(entityKey5, value5);
            map.put(entityKey6, value6);

            assertEquals(5, map.size());

            assertTrue(map.containsKey(entityKey1));
            assertTrue(map.containsKey(entityKey2));
            assertTrue(map.containsKey(entityKey3));
            assertTrue(map.containsKey(entityKey4));
            assertTrue(map.containsKey(entityKey5));
            assertTrue(map.containsKey(entityKey6));

            assertTrue(map.containsValue(value1));
            assertTrue(map.containsValue(value2));
            assertFalse(map.containsValue(value3));
            assertTrue(map.containsValue(value4));
            assertTrue(map.containsValue(value5));
            assertTrue(map.containsValue(value6));

            assertSame(value1, map.get(entityKey1));
            assertSame(value2, map.get(entityKey2));
            assertNotSame(value3, map.get(entityKey3));
            assertSame(value4, map.get(entityKey4));
            assertSame(value5, map.get(entityKey5));
            assertSame(value6, map.get(entityKey6));
      }

      @Test
      public void useRecordsWithPartiallyDuplicatedValues() {
            RecordEntity entityKey1 = getRecord(id1, name1, value1);
            RecordEntity entityKey2 = getRecord(id2, name2, value2);
            RecordEntity entityKey3 = getRecord(id3, name3, value3);
            RecordEntity entityKey4 = getRecord(id4, name3, value4);
            RecordEntity entityKey5 = getRecord(id5, name5, value5);
            RecordEntity entityKey6 = getRecord(id6, name6, value6);
            map.put(entityKey1, value1);
            map.put(entityKey2, value2);
            map.put(entityKey3, value3);
            map.put(entityKey4, value4);
            map.put(entityKey5, value5);
            map.put(entityKey6, value6);

            assertEquals(6, map.size());

            assertTrue(map.containsKey(entityKey1));
            assertTrue(map.containsKey(entityKey2));
            assertTrue(map.containsKey(entityKey3));
            assertTrue(map.containsKey(entityKey4));
            assertTrue(map.containsKey(entityKey5));
            assertTrue(map.containsKey(entityKey6));

            assertTrue(map.containsValue(value1));
            assertTrue(map.containsValue(value2));
            assertTrue(map.containsValue(value3));
            assertTrue(map.containsValue(value4));
            assertTrue(map.containsValue(value5));
            assertTrue(map.containsValue(value6));

            assertSame(value1, map.get(entityKey1));
            assertSame(value2, map.get(entityKey2));
            assertSame(value3, map.get(entityKey3));
            assertSame(value4, map.get(entityKey4));
            assertSame(value5, map.get(entityKey5));
            assertSame(value6, map.get(entityKey6));
      }
}