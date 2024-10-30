package com.wisecrm.crmapiservice.service.impl;

import com.wisecrm.crmapiservice.entity.*;
import com.wisecrm.crmapiservice.repository.*;
import com.wisecrm.crmapiservice.security.*;
import com.wisecrm.crmapiservice.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class ApiClientKeyControlImpl implements IApiClientKeyControl {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Autowired
    private ApiKeyService apiKeyService;

    @Override
    public String generate(Long companyId) {

        ApiKey apiKey;

        apiKey = apiKeyRepository.findByCompanyId(companyId).orElse( ApiKey.builder()
                .companyId(companyId)
                .valid(true)
                .build());

        String token = apiKeyService.generateToken(apiKey);

        apiKey.setKey(token);

        apiKeyRepository.save(apiKey);

        return token;
    }
}
