package kisonar.poc.framework.spring.domain;

public enum TaskState {

	ONGOING("Ongoing"),
	DONE("Done"),
	NEW("New"),
	POSTPONED("Postponed");

	private final String state;

	TaskState(String state) {
		this.state = state;
	}
}
