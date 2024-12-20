package com.wisecrm.employeeservice.security;

import org.keycloak.admin.client.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

//@Component
//
//public class KeycloakSecurityUtil {
//
//
//    Keycloak keycloak;
//
//    @Value("${server-url}")
//    private String serverUrl;
//
//    @Value("${realm}")
//    private String realm;
//
//    @Value("${client-id}")
//    private String clientId;
//
//    @Value("${grant-type}")
//    private String grantType;
//
//    @Value("${name}")
//    private String username;
//
//    @Value("${password}")
//    private String password;
//
//    @Value("${clientSecret}")
//    private String clientSecret;
//
//    public Keycloak getKeycloakInstance() {
//        if(keycloak == null) {
////            keycloak = KeycloakBuilder
////                    .builder().serverUrl(serverUrl).realm(realm)
////                    .clientId(clientId).grantType(grantType)
////                    .username(username).password(password).build();
//
//            keycloak =  Keycloak.getInstance(serverUrl,realm,username,password,clientId,clientSecret);
//        }
//
//
//        return keycloak;
//    }
//
//}
