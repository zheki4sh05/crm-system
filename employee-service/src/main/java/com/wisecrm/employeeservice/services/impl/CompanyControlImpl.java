package com.wisecrm.employeeservice.services.impl;

import com.wisecrm.employeeservice.dto.*;
import com.wisecrm.employeeservice.entity.*;
import com.wisecrm.employeeservice.exceptions.*;
import com.wisecrm.employeeservice.repository.*;
import com.wisecrm.employeeservice.services.interfaces.*;
import com.wisecrm.employeeservice.util.mapper.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import org.springframework.stereotype.*;

import java.sql.*;
import java.time.*;

@Service
public class CompanyControlImpl implements ICompanyControl {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private IObjectMapper objectMapper;

    private String createApiKey(){
        return "njknj";
    }

    @Override
    public Company create(CompanyDto companyDto) {

        if(companyRepository.findByName(companyDto.getName()).isEmpty()){

            String key = createApiKey();

            Company company = Company.builder()
                    .name(companyDto.getName())
                    .created(Timestamp.valueOf(LocalDateTime.now()))
                    .api_key(key)
                    .description(companyDto.getDescription())
                    .build();

             return companyRepository.save(company);

        }else{
            throw new SuchEntityAlreadyExists("Company with that name already exists!");
        }

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
    public CompanyDto updateCompany(CompanyDto companyDto) throws EntityNotFoundException, DataIntegrityViolationException {

        Company company = companyRepository.findById(companyDto.getId()).orElseThrow(EntityExistsException::new);

        company.setName(companyDto.getName());
        company.setDescription(companyDto.getDescription());

        companyRepository.save(company);

        return objectMapper.mapFrom(company);
    }
}
