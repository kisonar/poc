package kisonar.poc.framework.spring.security.config;

import kisonar.poc.framework.spring.security.controller.rest.web.JwtAuthenticationEntryPoint;
import kisonar.poc.framework.spring.security.controller.rest.web.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

      private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
      private final JwtRequestFilter jwtRequestFilter;
      private final CustomAuthenticationProvider customAuthenticationProvider;
      private final UserDetailsService jwtUserDetailsService;
      private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

      public WebSecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                               JwtRequestFilter jwtRequestFilter,
                               CustomAuthenticationProvider customAuthenticationProvider,
                               UserDetailsService jwtUserDetailsService) {
            this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
            this.jwtRequestFilter = jwtRequestFilter;
            this.customAuthenticationProvider = customAuthenticationProvider;
            this.jwtUserDetailsService = jwtUserDetailsService;
      }

      @Bean
      public AuthenticationManager authManager(HttpSecurity http) throws Exception {
            AuthenticationManagerBuilder authenticationManagerBuilder =
                    http.getSharedObject(AuthenticationManagerBuilder.class);
            authenticationManagerBuilder.userDetailsService(jwtUserDetailsService)
                    .passwordEncoder(passwordEncoder);
            authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
            return authenticationManagerBuilder.build();
      }

      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            // We don't need CSRF for this example
            httpSecurity
                    .csrf().disable()
                    .cors().disable()
                    // dont authenticate this particular request
                    .authorizeRequests().antMatchers("/authenticate").permitAll()
                    // all other requests need to be authenticated
                    .anyRequest().authenticated()
                    // make sure we use stateless session; session won't be used to
                    // store user's state.
                    .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            // Add a filter to validate the tokens with every request
            return httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).build();
      }
}