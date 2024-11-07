package com.wisecrm.customerservice.facade.impl;

import com.wisecrm.customerservice.dto.*;
import com.wisecrm.customerservice.entity.*;
import com.wisecrm.customerservice.exceptions.*;
import com.wisecrm.customerservice.facade.*;
import com.wisecrm.customerservice.service.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;


@Component
public class AuthUserDealControlFacade implements IAuthUserDealControlFacade {

    @Autowired
    private IDealControl dealControl;

    @Autowired
    private IStageCashControl stageCashControl;

    @Autowired
    private ICustomerControl customerControl;

    @Autowired
    private IDealTypeControl dealTypeControl;
    @Override
    public DealDto createDealByUserId(DealDto dto, Long userId) throws EntityNotFoundException {

        Long stageId = stageCashControl.findMinOrderStage().orElseThrow(EntityNotFoundException::new);

        dto.setStageId(stageId);

        Long companyId=1l;

        Customer customer = customerControl.findOrCreate(dto.getCustomerDto(), companyId);

        return dealControl.createDealByUserId(dto, userId, customer);
    }

    @Override
    public DealDto updateDeal(DealDto dealDto, Long userId) throws EntityNotFoundException {

        return dealControl.update(dealDto, userId);
    }

    @Override
    public DealTypeDto createDealType(DealTypeDto dto, Long userId) throws SuchEntityAlreadyExists {

        Long companyId=1l;

        return dealTypeControl.create(dto, companyId);
    }

    @Override
    public List<DealTypeDto> fetchDealTypes(Long userId) {

        Long companyId=1l;

        return dealTypeControl.fetchBycompanyId(companyId);
    }
}
