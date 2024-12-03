package com.wisecrm.gatewayserver.security;


//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
//    private String issuerUri;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(
//                        auth -> auth.requestMatchers("/auth/*").permitAll()
//                                .anyRequest().authenticated()
//                ).sessionManagement(
//                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                ).oauth2ResourceServer(
//                        resourceServer -> resourceServer.jwt(
//                                jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(
//                                        keycloakAuthConverter()
//                                )
//                        )
//                )
//                .build();
//
//    }
//
//    private Converter<Jwt,? extends AbstractAuthenticationToken> keycloakAuthConverter() {
//        var converter = new JwtAuthenticationConverter();
//        converter.setJwtGrantedAuthoritiesConverter(
//                new AuthoritiesConverter()
//        );
//        return converter;
//    }
//    @Bean
//    public JwtDecoder jwtDecoder() {
//
//        JwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(issuerUri).jwsAlgorithm(SignatureAlgorithm.RS512).build();
//
//        return token -> {
//            System.out.println("String token : "+token);
//            var t = jwtDecoder.decode(token);
//            System.out.println(t.toString());
//            return t;
//        };
//    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        return http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
//                .authorizeHttpRequests((requests)->requests
//                        .requestMatchers("/eureka/**").permitAll()
//                        .anyRequest().authenticated())
//                .oauth2ResourceServer(oauth->oauth.jwt(Customizer.withDefaults()))
//                .build();
//
//
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties){
//        return JwtDecoders.fromIssuerLocation(properties.getJwt().getIssuerUri());
//    }

//}
