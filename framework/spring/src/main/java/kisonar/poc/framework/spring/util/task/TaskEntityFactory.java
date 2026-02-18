package kisonar.poc.framework.spring.util.task;

import kisonar.poc.framework.spring.domain.jpa.task.TaskEntity;

public final class TaskEntityFactory {

      private TaskEntityFactory() {
      }

      public static TaskEntity getUserEntity(String name, String state) {
            return new TaskEntity(null, name, state);
      }

}