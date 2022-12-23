package kisonar.poc.framework.spring.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.time.Duration;

//does not work at GitHub default runner
@Disabled
@Testcontainers
public class IntegrationPrepare {

      @Container
      public static DockerComposeContainer environment =
              new DockerComposeContainer(new File("docker-compose.yml")).withLocalCompose(true)
                      .withExposedService("adminer_1", 8080, Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)))
                      .withExposedService("mariadb_1", 3306, Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)))
                      .withExposedService("kafka", 9092);

      @BeforeAll
      public static void start() {
            environment.start();
            verifyServiceStarted("adminer_1");
            verifyServiceStarted("mariadb_1");
      }

      @AfterAll
      public static void stop() {
            environment.stop();
      }

      private static void verifyServiceStarted(String serviceName) {
            var adminer = environment.getContainerByServiceName(serviceName);
            adminer.ifPresentOrElse(item -> {
                  System.out.println("Service " + serviceName + " available");
            }, () -> {
                  System.out.println("Service " + serviceName + " not available");
            });
      }
}