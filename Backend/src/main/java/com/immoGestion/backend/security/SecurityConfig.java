package com.immoGestion.backend.security;

import com.immoGestion.backend.services.securityService.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig (UserDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/logements/**").permitAll()
                        .requestMatchers("/admin/locataires/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll() // The main Swagger UI page
                        .requestMatchers("/swagger-ui/**").permitAll()   // Static resources (JS, CSS, images)
                        .requestMatchers("/v3/api-docs/**").permitAll()  // The OpenAPI JSON/YAML definitions
                        .requestMatchers("/api-docs/**").permitAll()     // Sometimes this path is also used
                        .requestMatchers("/webjars/**").permitAll()
                        .anyRequest().authenticated())
                .userDetailsService(userDetailsService)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
