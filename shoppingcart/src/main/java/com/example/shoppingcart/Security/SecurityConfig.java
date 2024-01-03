
package com.example.shoppingcart.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Value;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String issuer;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth//
        // 小心import 錯：import org.springframework.boot.web.servlet.DispatcherType;
        // import jakarta.servlet.DispatcherType; 先啱
        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()//
        .requestMatchers("/user/**", "/transaction/**", "/public/**")//
        .permitAll()//
        .anyRequest()//
        .authenticated()) // 用緊anyRequest，所有request都會受保護->question: 如何不受保護
        .csrf(csrf -> csrf.disable())//
        .cors(Customizer.withDefaults());
    http.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
        .jwt(jwt -> jwt.decoder(JwtDecoders.fromIssuerLocation(issuer))));
    return http.build();
  }
}
/*
 * requestMatchers : specify the endpoints that require authentication ("/user/**", "/shopping-cart/**", "/transaction/**").
 * 
 * authenticated() ensures that only authenticated users can access the specified endpoints.
 * 
 * anyRequest().permitAll() allows all other requests to be accessible without authentication.
 */
