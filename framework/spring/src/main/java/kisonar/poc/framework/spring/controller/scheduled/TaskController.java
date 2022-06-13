package kisonar.poc.framework.spring.controller.scheduled;

import kisonar.poc.framework.spring.domain.TaskState;
import kisonar.poc.framework.spring.domain.jpa.task.TaskEntity;
import kisonar.poc.framework.spring.service.task.TaskService;
import kisonar.poc.framework.spring.util.task.TaskEntityFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@EnableScheduling
@Controller
public class TaskController {

      private final AtomicInteger atomicInteger = new AtomicInteger();
      private final List<TaskState> VALUES = List.of(TaskState.values());
      private final Random RANDOM = new Random();
      private final TaskService taskService;

      public TaskController(TaskService taskService) {
            this.taskService = taskService;
      }

      @Scheduled(fixedDelay = 100, timeUnit = TimeUnit.SECONDS)
      void generateTasks() {
            int value = atomicInteger.incrementAndGet();
            String taskName = "Task" + value;
            String taskValue = VALUES.get(RANDOM.nextInt(VALUES.size())).name();
            TaskEntity taskEntity = TaskEntityFactory.getUserEntity(taskName, taskValue);
            taskService.add(taskEntity);
            System.out.println("Added entity " + taskEntity);
      }
}
