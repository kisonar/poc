package kisonar.poc.framework.spring;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.websocket.GuacamoleWebSocketTunnelEndpoint;
import org.springframework.stereotype.Component;

import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket-guacamole", subprotocols = "guacamole")
@Component
public class CustomGuacamoleWebSocketTunnelEndpoint extends GuacamoleWebSocketTunnelEndpoint {

      public CustomGuacamoleWebSocketTunnelEndpoint() {
      }

      @Override
      protected GuacamoleTunnel createTunnel(Session session, EndpointConfig config)

      throws GuacamoleException {
            return RasUtils.getSimpleGuacamoleTunnel();
      }

}