package com.wisecrm.customerservice.service.impl;

import com.wisecrm.customerservice.dto.*;
import com.wisecrm.customerservice.entity.*;
import com.wisecrm.customerservice.repository.*;
import com.wisecrm.customerservice.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class CustomerControlImpl implements ICustomerControl {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer saveEntity(Customer entity){
        return customerRepository.save(entity);
    }
    @Override
    public Customer findOrCreate(CustomerDto dto, Long id) {

        return customerRepository.findByEmail(dto.getEmail()).orElse(
                saveEntity(
                        Customer.builder()
                        .email(dto.getEmail())
                        .name(dto.getName())
                        .lastname(dto.getLastname())
                        .address(dto.getAddress())
                                .company_id(id)
                                .build()));

    }
}
