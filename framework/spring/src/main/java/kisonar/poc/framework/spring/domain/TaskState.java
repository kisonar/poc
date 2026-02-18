package kisonar.poc.framework.spring.domain;

public enum TaskState {

      ONGOING(),
      DONE(),
      NEW(),
      POSTPONED();

      TaskState() {
      }
}
