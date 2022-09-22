package kisonar.poc.framework.spring.config.ras;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleSocket;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleConfiguration;

public final class RasUtils {

      private RasUtils() {
      }

      public static GuacamoleTunnel getSimpleGuacamoleTunnel() throws GuacamoleException {
            try {
                  String guacdHost = "127.0.0.1";
                  int guacdPort = 4822;
                  InetGuacamoleSocket inetGuacamoleSocket = new InetGuacamoleSocket(guacdHost, guacdPort);
                  GuacamoleConfiguration configuration = new GuacamoleConfiguration();
                  configuration.setProtocol("vnc");
                  configuration.setParameter("hostname", "192.168.0.104");
                  configuration.setParameter("port", "5900");
                  //configuration.setParameter("username", "");
                  configuration.setParameter("password", "Marcin");
                  configuration.setParameter("ignore-cert", "true");

                  GuacamoleSocket socket = new ConfiguredGuacamoleSocket(inetGuacamoleSocket, configuration);
                  GuacamoleTunnel tunnel = new SimpleGuacamoleTunnel(socket);
                  return tunnel;

            }
            catch (Exception e) {
                  throw new GuacamoleException(e);
            }
      }
}
