package kisonar.poc.framework.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class SpringCustomConfiguration {

      //web socket bean
      @Bean
      public ServerEndpointExporter serverEndpointExporter() {
            return new ServerEndpointExporter();
      }
}