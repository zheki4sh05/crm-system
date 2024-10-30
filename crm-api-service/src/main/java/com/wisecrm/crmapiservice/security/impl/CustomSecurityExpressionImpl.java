package com.wisecrm.crmapiservice.security.impl;

import com.wisecrm.crmapiservice.entity.*;
import com.wisecrm.crmapiservice.security.*;
import com.wisecrm.crmapiservice.security.interfaces.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service("cse")
@RequiredArgsConstructor
public class CustomSecurityExpressionImpl implements IControlSecurityExpression {

    @Autowired
    private ApiKeyService jwtService;
    public ApiKey getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return (ApiKey) authentication.getPrincipal();
    }

}
