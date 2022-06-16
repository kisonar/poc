package kisonar.poc.framework.spring;

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

      public CustomGuacamoleHttpTunnelServlet() {
      }


      @Override
      protected GuacamoleTunnel doConnect(HttpServletRequest request)
              throws GuacamoleException {
            return RasUtils.getSimpleGuacamoleTunnel();
      }

}