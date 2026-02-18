package kisonar.poc.framework.spring.repository.task;

import jakarta.transaction.Transactional;
import kisonar.poc.framework.spring.domain.jpa.task.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}