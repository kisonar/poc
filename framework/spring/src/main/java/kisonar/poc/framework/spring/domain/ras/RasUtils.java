package kisonar.poc.framework.spring.domain.ras;

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
            String guacdHost = "192.168.0.1";
            int guacdPort = 4822;
            InetGuacamoleSocket inetGuacamoleSocket = new InetGuacamoleSocket(guacdHost, guacdPort);

            GuacamoleConfiguration configuration = new GuacamoleConfiguration();
            configuration.setProtocol("vnc");
            configuration.setParameter("hostname", "192.168.0.2");
            configuration.setParameter("port", "3389");
            configuration.setParameter("username", "administrator");
            configuration.setParameter("password", "123456");
            configuration.setParameter("ignore-cert", "true");

            GuacamoleSocket socket = new ConfiguredGuacamoleSocket(inetGuacamoleSocket, configuration);
            GuacamoleTunnel tunnel = new SimpleGuacamoleTunnel(socket);
            return tunnel;
      }

}
