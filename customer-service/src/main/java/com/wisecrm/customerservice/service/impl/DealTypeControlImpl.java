package com.wisecrm.customerservice.service.impl;

import com.wisecrm.customerservice.dto.*;
import com.wisecrm.customerservice.entity.*;
import com.wisecrm.customerservice.exceptions.*;
import com.wisecrm.customerservice.repository.*;
import com.wisecrm.customerservice.service.*;
import com.wisecrm.customerservice.util.mapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class DealTypeControlImpl implements IDealTypeControl {

    @Autowired
    private DealTypeRepository dealTypeRepository;

    @Autowired
    private IObjectMapper objectMapper;

    @Override
    public DealTypeDto create(DealTypeDto dto, Long companyId) throws SuchEntityAlreadyExists {

        if(dealTypeRepository.findByCompanyAndName(dto.getName(), companyId).isEmpty()){

            DealType dealType = DealType.builder()
                    .name(dto.getName())
                    .company(companyId)
                    .build();

            return objectMapper.mapFrom(dealTypeRepository.save(dealType));

        }else{
            throw new SuchEntityAlreadyExists("Deal type with name "+dto.getName()+" already exists!");
        }

    }

    @Override
    public List<DealTypeDto> fetchBycompanyId(Long companyId) {

        List<DealType> dealTypes = dealTypeRepository.findByCompanyId(companyId).orElse(new ArrayList<>());

        return dealTypes.stream().map(item->objectMapper.mapFrom(item)).collect(Collectors.toList());
    }
}
