package com.wisecrm.crmapiservice.service.impl;

import com.wisecrm.crmapiservice.dto.*;
import com.wisecrm.crmapiservice.entity.*;
import com.wisecrm.crmapiservice.repository.*;
import com.wisecrm.crmapiservice.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ApplicationControlImpl implements IApplicationControl {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public void create(ApplicationDto applicationDto, ApiKey apiKeyEntity) {

        Application application = Application.builder()
                .key(apiKeyEntity)
                .body(applicationDto.getBody())
                .build();

        applicationRepository.save(application);


    }

    @Override
    public String generate(Long companyId) {
        return null;
    }

    private ApplicationDto mapFromEntity(Application application){
        return ApplicationDto.builder()
                .body(application.getBody())
                .build();
    }

    @Override
    public List<ApplicationDto> getAllByApiKeyAndCompanyId(ApiKey apikeyEntity, Long companyId) {

        List<Application> applications = applicationRepository.findAllByApiKeyAndCompanyId(apikeyEntity.getKey(), companyId);

        List<ApplicationDto> applicationDtoList = new ArrayList<>();

        applications.forEach(item->{
            applicationDtoList.add(mapFromEntity(item));
        });

        applicationRepository.deleteAll(applications);

        return applicationDtoList;
    }


}
