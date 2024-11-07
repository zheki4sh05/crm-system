package com.wisecrm.employeeservice.controllers;

import com.wisecrm.employeeservice.dto.*;
import com.wisecrm.employeeservice.security.*;
import org.keycloak.admin.client.*;
import org.keycloak.representations.idm.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import jakarta.ws.rs.core.Response;

import java.util.*;


//@RestController
//@RequestMapping("/auth")
//public class AuthorizationController {
//
//    @Autowired
//    KeycloakSecurityUtil keycloakUtil;
//
//    @Value("${realm}")
//    private String realm;
//    @PostMapping(value = "/user")
//    public Response createUser(@RequestBody UserDto userDto) {
//        UserRepresentation userRep = mapUserRep(userDto);
//        Keycloak keycloak = keycloakUtil.getKeycloakInstance();
//        var res = keycloak.realm(realm).users().create(userRep);
//        return Response.ok(userDto).build();
//
//    }
//
//    private UserDto mapUser(UserRepresentation userRep) {
//        UserDto userDto = new UserDto();
//        userDto.setId(userRep.getId());
//        userDto.setFirstName(userRep.getFirstName());
//        userDto.setLastName(userRep.getLastName());
//        userDto.setEmail(userRep.getEmail());
//        userDto.setUserName(userRep.getUsername());
//        return userDto;
//    }
//
//    private UserRepresentation mapUserRep(UserDto userDto) {
//        UserRepresentation userRep = new UserRepresentation();
//        userRep.setId(userDto.getId());
//        userRep.setUsername(userDto.getUserName());
//        userRep.setFirstName(userDto.getFirstName());
//        userRep.setLastName(userDto.getLastName());
//        userRep.setEmail(userDto.getEmail());
//        userRep.setEnabled(true);
//        userRep.setEmailVerified(true);
//        List<CredentialRepresentation> creds = new ArrayList<>();
//        CredentialRepresentation cred = new CredentialRepresentation();
//        cred.setTemporary(false);
//        cred.setValue(userDto.getPassword());
//        creds.add(cred);
//        userRep.setCredentials(creds);
//        return userRep;
//    }
//
//
//}
