package com.wisecrm.employeeservice.services.interfaces;

import com.wisecrm.employeeservice.dto.*;

public interface ICompanyControl {
    CompanyDto create(CompanyDto companyDto);

    CompanyDto fetch(String email);

    String fetchKey(Long companyId);

    String generateKey(Long companyId);

    CompanyDto updateCompany(CompanyDto companyDto);
}
