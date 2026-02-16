package kisonar.poc.framework.spring.domain.jpa.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

import static kisonar.poc.framework.spring.domain.jpa.EntitytGenerationType.NATIVE;

@Entity
@Table(name = "tasks")
public class TaskEntity {

	private static final String TASK_ID = "taskId";
	private static final String TASK_NAME = "taskName";
	private static final String TASK_STATUS = "taskStatus";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = NATIVE)
	@Column(name = TASK_ID)
	private Long taskId;

	@Column(name = TASK_NAME)
	private String taskName;

	@Column(name = TASK_STATUS)
	private String taskStatus;

	public TaskEntity() {
	}

	public TaskEntity(Long taskId, String taskName, String taskStatus) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskStatus = taskStatus;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TaskEntity that = (TaskEntity) o;
		return taskId.equals(that.taskId) && taskName.equals(that.taskName) && taskStatus.equals(that.taskStatus);
	}

	@Override
	public int hashCode() {
		return Objects.hash(taskId, taskName, taskStatus);
	}

	@Override
	public String toString() {
		return "TaskEntity{" +
				"taskId=" + taskId +
				", taskName='" + taskName + '\'' +
				", taskStatus='" + taskStatus + '\'' +
				'}';
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
}
