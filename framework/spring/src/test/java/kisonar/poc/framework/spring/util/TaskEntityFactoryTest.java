package kisonar.poc.framework.spring.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskEntityFactoryTest {

      @Test
      public void testCreateTaskEntity() {
            var cut = TaskEntityFactory.getTaskEntity("OK", "cos");
            assertEquals("OK", cut.getTaskName());
      }
}