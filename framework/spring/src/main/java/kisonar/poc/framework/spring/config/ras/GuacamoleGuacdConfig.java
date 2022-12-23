package kisonar.poc.framework.spring.config.ras;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//not required in JAVA
//@EnableConfigurationProperties
//@ConstructorBinding
@Configuration
@ConfigurationProperties(prefix = "guacamole.guacd")
public class GuacamoleGuacdConfig {

      private String host;
      private Integer port;

      public String getHost() {
            return host;
      }

      public void setHost(String host) {
            this.host = host;
      }

      public Integer getPort() {
            return port;
      }

      public void setPort(Integer port) {
            this.port = port;
      }
}
