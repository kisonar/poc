package kisonar.poc.framework.spring.service.ras;

import kisonar.poc.framework.spring.config.ras.GuacamoleClientConfig;
import kisonar.poc.framework.spring.config.ras.GuacamoleGuacdConfig;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.springframework.stereotype.Service;

@Service
public class RasService {

      private final GuacamoleGuacdConfig guacamoleGuacdConfig;
      private final GuacamoleClientConfig guacamoleClientConfig;

      public RasService(GuacamoleGuacdConfig guacamoleGuacdConfig, GuacamoleClientConfig guacamoleClientConfig) {
            this.guacamoleGuacdConfig = guacamoleGuacdConfig;
            this.guacamoleClientConfig = guacamoleClientConfig;
      }

      public GuacamoleTunnel getSimpleGuacamoleTunnel() throws GuacamoleException {
            try {
                  var inetGuacamoleSocket = new InetGuacamoleSocket(guacamoleGuacdConfig.getHost(), guacamoleGuacdConfig.getPort());
                  var configuration = new GuacamoleConfiguration();
                  configuration.setProtocol(guacamoleClientConfig.getProtocol());
                  configuration.setParameter("hostname", guacamoleClientConfig.getHost());
                  configuration.setParameter("port", guacamoleClientConfig.getPort().toString());
                  //configuration.setParameter("username", "");
                  configuration.setParameter("password", guacamoleClientConfig.getPassword());
                  configuration.setParameter("ignore-cert", guacamoleClientConfig.getIgnoreCert().toString());
                  var socket = new ConfiguredGuacamoleSocket(inetGuacamoleSocket, configuration);
                  return new SimpleGuacamoleTunnel(socket);

            }
            catch (Exception e) {
                  throw new GuacamoleException(e);
            }
      }
}
