package com.wisecrm.employeeservice.facade.impl;


import com.wisecrm.employeeservice.dto.*;
import com.wisecrm.employeeservice.entity.*;
import com.wisecrm.employeeservice.facade.*;
import com.wisecrm.employeeservice.repository.*;
import com.wisecrm.employeeservice.services.interfaces.*;
import com.wisecrm.employeeservice.util.mapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class AuthUserDataControlFacadeImpl implements AuthUserDataControlFacade {

    @Autowired
    private ICompanyControl companyControl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IObjectMapper objectMapper;

    @Override
    public CompanyDto createCompany(CompanyDto companyDto, Long userId) {

        User user = userRepository.findByUserId(userId);

        Company company = companyControl.create(companyDto);

        user.setCompany(company);

        userRepository.save(user);

        return objectMapper.mapFrom(company);
    }

    @Override
    public CompanyDto fetch(Long userId) {

        User user = userRepository.findByUserId(userId);

        return objectMapper.mapFrom(user.getCompany());
    }

    @Override
    public Long companyUser(Long userId) {

        User user = userRepository.findByUserId(userId);

        return user.getCompany().getId();
    }
}
