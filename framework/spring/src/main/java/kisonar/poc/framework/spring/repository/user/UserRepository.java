package kisonar.poc.framework.spring.repository.user;

import jakarta.transaction.Transactional;
import kisonar.poc.framework.spring.domain.jpa.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}