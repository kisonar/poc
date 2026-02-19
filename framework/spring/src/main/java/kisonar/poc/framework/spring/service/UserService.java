package kisonar.poc.framework.spring.service;

import kisonar.poc.framework.spring.domain.jpa.user.UserEntity;
import kisonar.poc.framework.spring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

      private final UserRepository userRepository;

      public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
      }

      public Optional<UserEntity> getById(Long userId) {
            return userRepository.findById(userId);
      }

      public void add(UserEntity user) {
            userRepository.saveAndFlush(user);
      }
}