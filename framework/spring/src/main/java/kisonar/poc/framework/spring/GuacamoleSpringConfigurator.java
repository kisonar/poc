package kisonar.poc.framework.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpointConfig;

@Component
public class GuacamoleSpringConfigurator extends ServerEndpointConfig.Configurator implements
        ApplicationContextAware {

      private static volatile BeanFactory context;

      @Override
      public <T> T getEndpointInstance(Class<T> clazz) {
            return context.getBean(clazz);
      }

      @Override
      public void setApplicationContext(ApplicationContext applicationContext)
              throws BeansException {
            GuacamoleSpringConfigurator.context = applicationContext;
      }
}
