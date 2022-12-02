package kisonar.poc.framework.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class App {

      public static void main(String[] args) {
            SpringApplication.run(App.class, args);
      }
}