package com.wisecrm.customerservice.security;

import org.springframework.core.convert.converter.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;


import java.util.*;
import java.util.stream.*;

//public class AuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
//
//
//    @Override
//    public Collection<GrantedAuthority> convert(Jwt source) {
//        Map<String, Object> realmAccess = (Map<String, Object>) source.getClaims().get("realm_access");
//        if (realmAccess != null && realmAccess.containsKey("roles")) {
//            return ((Collection<String>) realmAccess.get("roles")).stream()
//                    .map(role -> "ROLE_" + role)
//                    .map(SimpleGrantedAuthority::new)
//                    .collect(Collectors.toList());
//        }
//        return Collections.emptyList();
//    }
//
//}
