package com.wisecrm.customerservice.util.mapper.impl;

import com.wisecrm.customerservice.dto.*;
import com.wisecrm.customerservice.entity.*;
import com.wisecrm.customerservice.util.mapper.*;
import org.springframework.stereotype.*;

@Component
public class IObjectMapperImpl implements IObjectMapper {
    @Override
    public DealDto mapFrom(Deal save) {

        return DealDto.builder()
                .name(save.getName())
                .customerDto(mapFrom(save.getCustomer()))
                .description(save.getDescription())
                .stageId(save.getStage())
                .started(save.getStarted().toString())
                .build();

    }

    @Override
    public CustomerDto mapFrom(Customer customer){
        return CustomerDto.builder()
                .email(customer.getEmail())
                .name(customer.getName())
                .lastname(customer.getLastname())
                .address(customer.getAddress())
                .build();
    }

    @Override
    public DealTypeDto mapFrom(DealType dealType) {

        return DealTypeDto.builder()
                .id(dealType.getId())
                .name(dealType.getName())
                .build();
    }

}
