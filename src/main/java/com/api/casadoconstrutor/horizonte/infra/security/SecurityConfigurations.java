package com.api.casadoconstrutor.horizonte.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors().and()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/horizonte/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/horizonte/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/horizonte/vistas/upload").hasAnyRole("ADMIN", "ADMIN_VISTAS")
                        .requestMatchers(HttpMethod.DELETE, "/api/horizonte/vistas/delete/{id}").hasAnyRole("ADMIN", "ADMIN_VISTAS")
                        .requestMatchers(HttpMethod.GET, "/api/horizonte/vistas/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/horizonte/solicitacoes").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/horizonte/solicitacoes").hasRole("USER")
                        .requestMatchers(HttpMethod.PATCH, "/api/horizonte/solicitacoes/*/status").hasAnyRole("ADMIN", "ADMIN_SGHT")
                        .requestMatchers(HttpMethod.DELETE, "/api/horizonte/solicitacoes/*").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/horizonte/solicitacoes/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/horizonte/solicitacoes/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/horizonte/solicitacoes/nome/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/horizonte/solicitacoes/motivo/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/horizonte/horas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/horizonte/horas/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/horizonte/horas").hasAnyRole("ADMIN", "ADMIN_SGHT")
                        .requestMatchers(HttpMethod.PATCH, "/api/horizonte/horas/*").hasAnyRole("ADMIN", "ADMIN_SGHT")
                        .requestMatchers(HttpMethod.PUT, "/api/horizonte/horas").hasAnyRole("ADMIN","ADMIN_SGHT")
                        .requestMatchers(HttpMethod.DELETE, "/api/horizonte/horas").hasAnyRole("ADMIN","ADMIN_SGHT")
                        .requestMatchers(HttpMethod.GET, "/api/horizonte/comprovantes/view/*").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("http://24.144.93.247"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



}
