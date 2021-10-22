package kisonar.poc.framework.spring.controller;


//import org.apache.log4j.Logger;
//import org.apache.log4j.LogManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  //private static final Logger LOG = LogManager.getLogger(Controller.class);

  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    //LOG.info("Invoking endpoint /hello");
    return String.format("Hello %s!", name);
  }

}