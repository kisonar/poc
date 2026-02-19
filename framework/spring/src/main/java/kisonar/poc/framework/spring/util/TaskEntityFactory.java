package kisonar.poc.framework.spring.util;

import kisonar.poc.framework.spring.domain.jpa.task.TaskEntity;

public final class TaskEntityFactory {

      private TaskEntityFactory() {
      }

      public static TaskEntity getTaskEntity(String name, String state) {
            return new TaskEntity(null, name, state);
      }

}