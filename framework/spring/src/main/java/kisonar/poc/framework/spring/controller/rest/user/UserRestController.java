package kisonar.poc.framework.spring.controller.rest.user;

import kisonar.poc.framework.spring.domain.jpa.user.UserEntity;
import kisonar.poc.framework.spring.service.user.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserRestController {

	private final UserService userService;

	public UserRestController(UserService userService) {
		this.userService = userService;

	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserEntity> getUserById(@RequestParam(value = "userId") Long userId) {
		Optional<UserEntity> result = userService.getById(userId);
		return userService.getById(userId).isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.notFound().build();
	}
}