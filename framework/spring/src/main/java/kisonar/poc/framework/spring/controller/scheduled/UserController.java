package kisonar.poc.framework.spring.controller.scheduled;

import kisonar.poc.framework.spring.service.user.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

//@EnableScheduling
@Controller
public class UserController {

	private final AtomicInteger atomicInteger = new AtomicInteger();
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}


	@Scheduled(fixedDelay = 100, timeUnit = TimeUnit.SECONDS)
	void generateUsers() {
		int value = atomicInteger.incrementAndGet();
		String stringValue = "test" + value;
          //var userEntity = UserEntityFactory.getUserEntity(stringValue, stringValue, stringValue);
          //userService.add(userEntity);
          //System.out.println("Added entity " + userEntity);
	}
}
