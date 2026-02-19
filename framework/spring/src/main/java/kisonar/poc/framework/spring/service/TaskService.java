package kisonar.poc.framework.spring.service;

import kisonar.poc.framework.spring.domain.jpa.task.TaskEntity;
import kisonar.poc.framework.spring.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

      private final TaskRepository userRepository;

      public TaskService(TaskRepository userRepository) {
            this.userRepository = userRepository;
      }

      public Optional<TaskEntity> getById(Long userId) {
            return userRepository.findById(userId);
      }

      public void add(TaskEntity userEntity) {
            userRepository.saveAndFlush(userEntity);
      }
}