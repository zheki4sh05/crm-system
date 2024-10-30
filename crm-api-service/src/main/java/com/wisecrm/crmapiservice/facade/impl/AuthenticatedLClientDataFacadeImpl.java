package com.wisecrm.crmapiservice.facade.impl;

import com.wisecrm.crmapiservice.dto.*;
import com.wisecrm.crmapiservice.entity.*;
import com.wisecrm.crmapiservice.facade.*;
import com.wisecrm.crmapiservice.repository.*;
import com.wisecrm.crmapiservice.security.interfaces.*;
import com.wisecrm.crmapiservice.service.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class AuthenticatedLClientDataFacadeImpl implements IAuthenticatedClientDataFacade {

    @Autowired
    private IAuthClientData authenticatedClientData;

    @Autowired
    private IApplicationControl applicationControl;

    @Autowired
    private ApiKeyRepository apiKeyRepository;


    @Override
    public void create(ApplicationDto applicationDto) {

        ApiKey apiKey = authenticatedClientData.getCurrentUser();

        ApiKey apiKeyEntity =apiKeyRepository.findByKey(apiKey.getKey()).get();

        applicationControl.create(applicationDto, apiKeyEntity);


    }

    @Override
    public List<ApplicationDto> getAllByApiKey(String apiKey, Long companyId) throws EntityNotFoundException {

        ApiKey apikeyEntity = apiKeyRepository.findByKey(apiKey).orElseThrow(EntityExistsException::new);


        return applicationControl.getAllByApiKeyAndCompanyId(apikeyEntity,companyId);
    }
}
