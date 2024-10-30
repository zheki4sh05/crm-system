package com.wisecrm.employeeservice.controllers;

import com.wisecrm.authorizationservice.dto.*;
import com.wisecrm.authorizationservice.security.*;
import com.wisecrm.employeeservice.dto.*;
import com.wisecrm.employeeservice.security.*;
import org.keycloak.admin.client.*;
import org.keycloak.representations.idm.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import jakarta.ws.rs.core.Response;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    KeycloakSecurityUtil keycloakUtil;

    @Value("${realm}")
    private String realm;
    @PostMapping(value = "/user")
    public Response createUser(@RequestBody User user) {
        UserRepresentation userRep = mapUserRep(user);
        Keycloak keycloak = keycloakUtil.getKeycloakInstance();
        var res = keycloak.realm(realm).users().create(userRep);
        return Response.ok(user).build();

    }

    private User mapUser(UserRepresentation userRep) {
        User user = new User();
        user.setId(userRep.getId());
        user.setFirstName(userRep.getFirstName());
        user.setLastName(userRep.getLastName());
        user.setEmail(userRep.getEmail());
        user.setUserName(userRep.getUsername());
        return user;
    }

    private UserRepresentation mapUserRep(User user) {
        UserRepresentation userRep = new UserRepresentation();
        userRep.setId(user.getId());
        userRep.setUsername(user.getUserName());
        userRep.setFirstName(user.getFirstName());
        userRep.setLastName(user.getLastName());
        userRep.setEmail(user.getEmail());
        userRep.setEnabled(true);
        userRep.setEmailVerified(true);
        List<CredentialRepresentation> creds = new ArrayList<>();
        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setTemporary(false);
        cred.setValue(user.getPassword());
        creds.add(cred);
        userRep.setCredentials(creds);
        return userRep;
    }


}
