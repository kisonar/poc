package kisonar.poc.framework.spring.controller.ras;

import kisonar.poc.framework.spring.service.ras.RasService;
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

      private final String HARDCODED_ID = "id";
      private final RasService rasService;

      public CustomGuacamoleWebSocketTunnelEndpoint(RasService rasService) {
            this.rasService = rasService;
      }

      @Override
      protected GuacamoleTunnel createTunnel(Session session, EndpointConfig config)
              throws GuacamoleException {
            var params = session.getRequestParameterMap();
            var values = params.get(HARDCODED_ID);
            values.forEach(it -> System.out.println("Received parameter: " + it));
            return rasService.getSimpleGuacamoleTunnel();
      }
}