package com.wisecrm.gatewayserver.security;


import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.core.convert.converter.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.reactive.*;
import org.springframework.security.config.http.*;

import org.springframework.security.oauth2.jose.jws.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.*;
import org.springframework.security.web.*;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/auth/*").permitAll()
                                .anyRequest().authenticated()
                ).sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).oauth2ResourceServer(
                        resourceServer -> resourceServer.jwt(
                                jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(
                                        keycloakAuthConverter()
                                )
                        )
                )
                .build();
    }

    private Converter<Jwt,? extends AbstractAuthenticationToken> keycloakAuthConverter() {
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(
                new AuthoritiesConverter()
        );
        return converter;
    }
    @Bean
    public JwtDecoder jwtDecoder() {

        JwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(issuerUri).jwsAlgorithm(SignatureAlgorithm.RS512).build();

        return token -> {
            System.out.println("String token : "+token);
            var t = jwtDecoder.decode(token);
            System.out.println(t.toString());
            return t;
        };
    }

}
