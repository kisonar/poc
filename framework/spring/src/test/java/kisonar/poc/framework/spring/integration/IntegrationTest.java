package kisonar.poc.framework.spring.integration;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;

public class IntegrationTest {

      @ClassRule
      public static DockerComposeContainer environment =
              new DockerComposeContainer(new File("docker-compose.yml"))
                      .withExposedService("kafka", 9092);


      @Test
      public void simpleTest() {

      }

      @AfterAll
      public void tearDown() {
            environment.close();
      }

}
