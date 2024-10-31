package com.wisecrm.crmapiservice.service;

import com.wisecrm.crmapiservice.dto.*;
import com.wisecrm.crmapiservice.entity.*;
import jakarta.persistence.*;

public interface ICustomerControl {
    void add(ApiKey apiKey, CustomerDto customerDto);
    void update(ApiKey apiKey, CustomerDto customerDto) throws EntityNotFoundException;
}
