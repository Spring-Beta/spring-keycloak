package com.example.springkeycloak.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Value("${spring.security.oauth2.client.provider.keycloak.jwk-set-uri}")
    private String jwkSetUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for APIs
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configure CORS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll() // Allow public paths
                        .anyRequest().authenticated() // Authenticate all other requests
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt); // Configure JWT-based authentication
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }

    // CORS Configuration
    private UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:5173"); // Allow frontend origin
        corsConfiguration.addAllowedMethod("*"); // Allow all HTTP methods
        corsConfiguration.addAllowedHeader("*"); // Allow all headers
        corsConfiguration.setAllowCredentials(true); // Allow cookies or credentials if required

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
