package kisonar.poc.framework.spring.controller.ras;

import kisonar.poc.framework.spring.service.ras.RasService;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.servlet.GuacamoleHTTPTunnelServlet;

import javax.servlet.http.HttpServletRequest;

// Does not work http at all.
// when activated, WebSocket just gets stuck...

//@RestController
//@RequestMapping(path = "/tunnel", method = {RequestMethod.POST, RequestMethod.GET})
//@WebServlet //mandatory !!
public class CustomGuacamoleHttpTunnelServlet extends GuacamoleHTTPTunnelServlet {

      private final RasService rasService;

      public CustomGuacamoleHttpTunnelServlet(RasService rasService) {
            this.rasService = rasService;
      }

      @Override
      protected GuacamoleTunnel doConnect(HttpServletRequest request)
              throws GuacamoleException {
            return rasService.getSimpleGuacamoleTunnel();
      }

}