package com.wisecrm.crmapiservice.security.interfaces;

import com.wisecrm.crmapiservice.entity.*;

import java.util.*;

public interface IAuthClientData {

//    Optional<User> getUserByEmail(String email);
//
//    String getCurrentUserEmail();

    ApiKey getCurrentUser();

//    UserDto mapFrom(User addUser);
}

