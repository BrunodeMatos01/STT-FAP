package com.example.STTFAP.STTFAP.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Habilita CORS usando a configuração definida em corsConfigurationSource()
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // Desabilita CSRF (útil para testes; em produção pense em habilitar e usar token)
                .csrf(csrf -> csrf.disable())
                // Permite todas as requisições (sem autenticação)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOriginPatterns(List.of("*"));       // permite qualquer origem
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")); // métodos liberados
        config.setAllowedHeaders(List.of("*"));               // todos os headers liberados
        config.setAllowCredentials(true);                     // aceita cookies/credenciais
        config.setMaxAge(3600L);                               // cache do preflight

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);      // aplica para todas rotas

        return source;
    }
}
