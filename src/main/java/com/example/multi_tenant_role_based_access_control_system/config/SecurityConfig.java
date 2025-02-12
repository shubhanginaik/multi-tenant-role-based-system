package com.example.multi_tenant_role_based_access_control_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf((csrf) -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .anyRequest()
            .permitAll()) // Permit all requests without authentication(testing on Postman
        .sessionManagement((sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)));
    return http.build();
  }
}
