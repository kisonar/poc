package kisonar.poc.framework.spring.config.ras;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "guacamole.client")
public class GuacamoleClientConfig {

      private String host;
      private Integer port;
      private String protocol;
      private String password;
      private Boolean ignoreCert;

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

      public String getProtocol() {
            return protocol;
      }

      public void setProtocol(String protocol) {
            this.protocol = protocol;
      }

      public String getPassword() {
            return password;
      }

      public void setPassword(String password) {
            this.password = password;
      }

      public Boolean getIgnoreCert() {
            return ignoreCert;
      }

      public void setIgnoreCert(Boolean ignoreCert) {
            this.ignoreCert = ignoreCert;
      }
}