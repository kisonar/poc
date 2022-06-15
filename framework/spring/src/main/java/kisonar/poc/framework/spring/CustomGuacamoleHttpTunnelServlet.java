package kisonar.poc.framework.spring;

import kisonar.poc.framework.spring.util.ras.RasUtils;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.servlet.GuacamoleHTTPTunnelServlet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(path = "/tunnel", method = {RequestMethod.POST, RequestMethod.GET})
@WebServlet //mandatory !!
public class CustomGuacamoleHttpTunnelServlet extends GuacamoleHTTPTunnelServlet {

      public CustomGuacamoleHttpTunnelServlet() {
      }


      @Override
      protected GuacamoleTunnel doConnect(HttpServletRequest request)
              throws GuacamoleException {
            return RasUtils.getSimpleGuacamoleTunnel();
      }

}