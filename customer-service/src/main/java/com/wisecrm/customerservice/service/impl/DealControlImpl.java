package com.wisecrm.customerservice.service.impl;

import com.wisecrm.customerservice.dto.*;
import com.wisecrm.customerservice.entity.*;
import com.wisecrm.customerservice.repository.*;
import com.wisecrm.customerservice.service.*;
import com.wisecrm.customerservice.util.mapper.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import java.sql.Timestamp;
import java.time.*;
import java.util.*;


@Service
public class DealControlImpl implements IDealControl {

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private IObjectMapper objectMapper;

    @Override
    public DealDto createDealByUserId(DealDto dto, Long userId, Customer customer) {

        Deal deal = Deal.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .started(Timestamp.valueOf(LocalDateTime.now()))
                .employee_id(userId)
                .stage(dto.getStageId())
                .customer(customer)
                        .build();

        return objectMapper.mapFrom(dealRepository.save(deal));
    }

    @Override
    public DealDto update(DealDto dealDto, Long userId) throws EntityNotFoundException {

        Deal deal = dealRepository.findById(dealDto.getId()).orElseThrow(EntityNotFoundException::new);

        deal.setName(dealDto.getName());
        deal.setDescription(dealDto.getDescription());
        deal.setStage(dealDto.getStageId());

        return objectMapper.mapFrom(dealRepository.save(deal));
    }

    @Override
    public List<DealDto> fetchByUser(Long userId) {


        List<Deal> deals = dealRepository.findAllByUserId(userId).orElse(new ArrayList<>());

        return null;
    }
}
