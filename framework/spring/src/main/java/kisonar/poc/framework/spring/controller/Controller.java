package kisonar.poc.framework.spring.controller;


//import org.apache.log4j.Logger;
//import org.apache.log4j.LogManager;

import kisonar.poc.framework.spring.domain.jpa.UserEntity;
import kisonar.poc.framework.spring.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class Controller {

	//private static final Logger LOG = LogManager.getLogger(Controller.class);

	private final UserService userService;

	public Controller(UserService userService) {
		this.userService = userService;

	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		//LOG.info("Invoking endpoint /hello");
		return String.format("Hello %s!", name);
	}

	@GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserEntity> getUserById(@RequestParam(value = "userId") Long userId) {
		Optional<UserEntity> result = userService.getUserById(userId);
		return userService.getUserById(userId).isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.notFound().build();
	}

}