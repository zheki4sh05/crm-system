package com.wisecrm.crmapiservice.facade;

import com.wisecrm.crmapiservice.dto.*;
import com.wisecrm.crmapiservice.exceptions.*;
import jakarta.persistence.*;

import java.util.*;

public interface IAuthenticatedClientDataFacade {
    void create(ApplicationDto applicationDto);

    List<ApplicationDto> getAllByApiKey(String apiKey, Long companyId);

    void addCustomer(CustomerDto customerDto) throws SuchEntityAlreadyExistsException;

    void updateCustomer(CustomerDto customerDto) throws EntityNotFoundException;
}
