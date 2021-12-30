package kisonar.poc.framework.spring.controller;

import kisonar.poc.framework.spring.domain.jpa.user.UserEntity;
import kisonar.poc.framework.spring.service.user.UserService;
import kisonar.poc.framework.spring.util.user.UserEntityFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@EnableScheduling
@Controller
public class UserController {

	private final AtomicInteger atomicInteger = new AtomicInteger();
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Scheduled(fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
	void rocco() {
		int value = atomicInteger.incrementAndGet();
		String stringValue = "test" + value;
		UserEntity userEntity = UserEntityFactory.getUserEntity(stringValue, stringValue, stringValue);
		userService.add(userEntity);
		System.out.println("Added entity " + userEntity);
	}
}
