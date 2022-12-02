package kisonar.poc.framework.spring.security.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredRestController {

      @GetMapping("/secured")
      public String hello(@RequestParam(value = "name", defaultValue = "Secured default") String name) {
            return String.format("Hello %s!", name);
      }
}
