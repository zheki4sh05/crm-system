package com.wisecrm.crmapiservice.security;

import lombok.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.*;
import org.springframework.security.core.context.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.logout.*;
import org.springframework.web.cors.*;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import java.util.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    /**
     * Конфигурация Spring Security для веб-приложения.
     *
     * @param http HttpSecurity объект для настройки правил безопасности.
     * @return SecurityFilterChain, определяющий, какие запросы разрешены или запрещены.
     * @throws Exception если возникли ошибки при настройке.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                // Включаем поддержку CORS (Cross-Origin Resource Sharing)
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)// Отключаем CSRF (Cross-Site Request Forgery) защиту
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,"/api/v1/key/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/api-docs/**","/swagger-resources/**").permitAll()
                        .anyRequest().authenticated()
                )
                // Настраиваем управление сессиями (в данном случае, без состояния)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Предоставляем собственный провайдер аутентификации
                .authenticationProvider(authenticationProvider)
                // Добавляем фильтр для обработки JWT-токен
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(((request, response, authentication) ->
                        SecurityContextHolder.clearContext()
                ));
        return http.build();
    }

    //конфигурация для обработки cors
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(false);
        config.applyPermitDefaultValues();

        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
