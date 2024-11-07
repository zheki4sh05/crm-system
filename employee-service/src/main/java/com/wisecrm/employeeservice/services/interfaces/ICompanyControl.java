package com.wisecrm.employeeservice.services.interfaces;

import com.wisecrm.employeeservice.dto.*;
import com.wisecrm.employeeservice.entity.*;

public interface ICompanyControl {
    Company create(CompanyDto companyDto);

    String fetchKey(Long companyId);

    String generateKey(Long companyId);

    CompanyDto updateCompany(CompanyDto companyDto);
}
