package com.wisecrm.crmapiservice.security.impl;

import com.wisecrm.crmapiservice.entity.*;
import com.wisecrm.crmapiservice.repository.*;
import com.wisecrm.crmapiservice.security.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
@Service
public class AuthClientDataImpl implements IAuthClientData {


        @Autowired
        private ApiKeyRepository apiKeyRepository;

        @Autowired
        private IControlSecurityExpression customSecurityExpression;

        //возвращает пользователя по его адресу электронной почты

        @Override
        public ApiKey getCurrentUser() {
            return customSecurityExpression.getPrincipal();
        }
        //преобразует объект пользователя в DTO (UserDto), заполняя его поля.


}
