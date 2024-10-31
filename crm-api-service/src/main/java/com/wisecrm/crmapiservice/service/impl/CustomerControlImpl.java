package com.wisecrm.crmapiservice.service.impl;


import com.wisecrm.crmapiservice.dto.*;
import com.wisecrm.crmapiservice.entity.*;
import com.wisecrm.crmapiservice.exceptions.*;
import com.wisecrm.crmapiservice.repository.*;
import com.wisecrm.crmapiservice.service.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class CustomerControlImpl implements ICustomerControl {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void add(ApiKey apiKey, CustomerDto customerDto) throws SuchEntityAlreadyExistsException{



        if(customerRepository.getByEmail(customerDto.getEmail()).isEmpty()){
            Customer customer = Customer.builder()
                    .body(customerDto.getBody())
                    .status(Status.CREATED.value())
                    .key(apiKey)
                    .email(customerDto.getEmail())
                    .phone(customerDto.getPhone())
                    .build();

            customerRepository.save(customer);
        }else{
            throw new SuchEntityAlreadyExistsException("customer with such already exists");
        }



    }
    @Override
    public void update(ApiKey apiKey, CustomerDto customerDto) throws EntityNotFoundException{

        Customer customer =  customerRepository.getByEmail(customerDto.getEmail()).orElseThrow(EntityNotFoundException::new);

        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
        customer.setBody(customerDto.getBody());
        customer.setStatus(Status.UPDATED.value());

        customerRepository.save(customer);

    }
}
