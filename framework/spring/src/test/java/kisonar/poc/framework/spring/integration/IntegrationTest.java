package kisonar.poc.framework.spring.integration;

import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;

public class IntegrationTest {

      @ClassRule
      public static DockerComposeContainer environment =
              new DockerComposeContainer(new File("docker-compose.yml"))
                      .withExposedService("kafka", 9092);

      @BeforeAll
      public static void setUp() {
            environment.start();
      }

      @AfterAll
      public static void afterAll() {
            environment.stop();
            environment.close();
      }

      @Test
      public void simpleTest() {


      }

}
