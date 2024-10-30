package com.wisecrm.customerservice.security;


import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.*;


import org.springframework.security.web.*;


@Configuration // данный класс будет считан как конфиг для spring контейнера
@EnableWebSecurity// включает механизм защиты адресов, которые настраиваются в SecurityFilterChain
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        // подключаем конвертер ролей
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KCRoleConverter());

        return http
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/auth/*").permitAll()
                                .requestMatchers("/swagger-ui/**", "/api-docs/**","/swagger-resources/**").permitAll()
                                .requestMatchers("/api/v1/**").permitAll()
                                .anyRequest().authenticated()
                ).sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                //.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)))
                .build();

    }


}
