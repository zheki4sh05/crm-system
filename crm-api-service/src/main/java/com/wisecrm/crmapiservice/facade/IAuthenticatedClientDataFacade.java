package com.wisecrm.crmapiservice.facade;

import com.wisecrm.crmapiservice.dto.*;

import java.util.*;

public interface IAuthenticatedClientDataFacade {
    void create(ApplicationDto applicationDto);

    List<ApplicationDto> getAllByApiKey(String apiKey, Long companyId);
}
