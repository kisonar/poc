package kisonar.poc.framework.spring.repository;

import kisonar.poc.framework.spring.util.UserEntityFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

      @Autowired
      private UserRepository userRepository;

      @Test
      public void saveTask() {
            String name = "user99";
            String password = "user99pass";
            String emial = "user99email";
            var user = UserEntityFactory.getUserEntity(name, password, emial);

            var savedUser = userRepository.save(user);

            var foundTask = userRepository.findById(savedUser.getUserId()).orElseGet(Assertions::fail);
            Assertions.assertEquals(savedUser.getUserId(), foundTask.getUserId());
      }
}