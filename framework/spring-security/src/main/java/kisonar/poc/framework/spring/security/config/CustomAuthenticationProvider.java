package kisonar.poc.framework.spring.security.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

      @Override
      public Authentication authenticate(Authentication authentication)
              throws AuthenticationException {
            String name = authentication.getName();
            String password = authentication.getCredentials().toString();
            //TODO
            if (true) {
                  // use the credentials
                  // and authenticate against the third-party system
                  return new UsernamePasswordAuthenticationToken(
                          name, password, new ArrayList<>());
            } else {
                  return null;
            }
      }

      @Override
      public boolean supports(Class<?> authentication) {
            return authentication.equals(UsernamePasswordAuthenticationToken.class);
      }
}