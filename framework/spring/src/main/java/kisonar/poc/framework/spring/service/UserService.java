package kisonar.poc.framework.spring.service;

import kisonar.poc.framework.spring.domain.jpa.UserEntity;
import kisonar.poc.framework.spring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> getUserById(Long userId) {
        Optional<UserEntity> result = userRepository.findById(userId);
        return result;

    }

    //public UserEntity getByName(String userName){
    //    return userRepository.findByUserId(userId);
    //}

}