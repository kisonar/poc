package kisonar.poc.framework.spring.config.ras;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class GuacamoleConfiguration {

      @Bean
      public ServerEndpointExporter serverEndpointExporter() {
            return new ServerEndpointExporter();
      }
}
