package kisonar.poc.framework.spring.repository;

import kisonar.poc.framework.spring.domain.jpa.task.TaskEntity;
import kisonar.poc.framework.spring.util.TaskEntityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskRepositoryTest {

      private final String name = "TaskName";
      private final String state = "TaskState";

      @Autowired
      private TaskRepository taskRepository;

      @BeforeEach
      public void setUp() {
            taskRepository.deleteAll();
      }

      @Test
      public void saveTask() {
            TaskEntity task = TaskEntityFactory.getTaskEntity(name, state);

            //var savedTAsk = taskRepository.save(task);
            //var foundTask = taskRepository.findById(savedTAsk.getTaskId()).orElseGet(Assertions::fail);

            //Assertions.assertEquals(savedTAsk.getTaskId(), foundTask.getTaskId());
      }
}
