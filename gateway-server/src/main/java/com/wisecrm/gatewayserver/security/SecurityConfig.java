//package com.wisecrm.gatewayserver.security;
//
//import org.springframework.context.annotation.*;
//import org.springframework.security.config.annotation.web.builders.*;
//import org.springframework.security.config.annotation.web.configuration.*;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfiguration {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests()
//                .antMatchers("/company/create", "/company/**").permitAll()  // Разрешить доступ к этим маршрутам без авторизации
//                .anyRequest().authenticated();
//    }
//}