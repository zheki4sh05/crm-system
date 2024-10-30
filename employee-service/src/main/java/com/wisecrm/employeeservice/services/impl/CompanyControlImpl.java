package com.wisecrm.employeeservice.services.impl;

import com.wisecrm.employeeservice.dto.*;
import com.wisecrm.employeeservice.services.interfaces.*;
import org.springframework.stereotype.*;

@Service
public class CompanyControlImpl implements ICompanyControl {
    @Override
    public CompanyDto create(CompanyDto companyDto) {
        return null;
    }

    @Override
    public CompanyDto fetch(String email) {
        return null;
    }

    @Override
    public String fetchKey(Long companyId) {
        return null;
    }

    @Override
    public String generateKey(Long companyId) {
        return null;
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) {
        return null;
    }
}
