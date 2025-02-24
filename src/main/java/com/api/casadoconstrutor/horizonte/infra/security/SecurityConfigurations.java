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
                        .requestMatchers(HttpMethod.POST, "/horizonte/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/horizonte/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/horizonte/vistas/upload").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/horizonte/vistas/delete/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/horizonte/vistas/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/horizonte/solicitacoes").permitAll()
                        .requestMatchers(HttpMethod.POST, "/horizonte/solicitacoes").hasRole("USER")
                        .requestMatchers(HttpMethod.PATCH, "/horizonte/solicitacoes/*/status").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/horizonte/solicitacoes/*").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/horizonte/solicitacoes/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/horizonte/solicitacoes/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/horizonte/solicitacoes/nome/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/horizonte/solicitacoes/motivo/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/horizonte/horas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/horizonte/horas/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/horizonte/horas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/horizonte/horas/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/horizonte/horas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/horizonte/horas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/horizonte/comprovantes/view/*").permitAll()
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

        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



}
