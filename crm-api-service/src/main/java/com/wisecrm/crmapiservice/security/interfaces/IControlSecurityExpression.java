package com.wisecrm.crmapiservice.security.interfaces;

import com.wisecrm.crmapiservice.entity.*;

import java.util.*;

public interface IControlSecurityExpression {

    ApiKey getPrincipal();
//    boolean canAccessUser(Map<String, String> headers);
//    String getUserEmail(Map<String, String> headers);

}
