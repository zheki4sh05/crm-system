package com.wisecrm.crmapiservice.service;

import com.wisecrm.crmapiservice.dto.*;
import com.wisecrm.crmapiservice.entity.*;

import java.util.*;

public interface IApplicationControl {
    void create(ApplicationDto applicationDto, ApiKey apiKeyEntity);

    String generate(Long companyId);

    List<ApplicationDto> getAllByApiKeyAndCompanyId(ApiKey apikeyEntity, Long companyId);
}
