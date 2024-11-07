package com.wisecrm.employeeservice.facade;

import com.wisecrm.employeeservice.dto.*;

public interface AuthUserDataControlFacade {
    CompanyDto createCompany(CompanyDto companyDto, Long userId);

    CompanyDto fetch(Long userId);

    Long companyUser(Long userId);
}
