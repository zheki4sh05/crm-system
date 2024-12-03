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
import java.util.stream.*;


@Service
public class DealControlImpl implements IDealControl {

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private IObjectMapper objectMapper;

    @Autowired
    private OrderRepository orderRepository;

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

        return objectMapper.mapFrom(dealRepository.save(deal), new ArrayList<>());
    }

    @Override
    public DealDto update(DealDto dealDto, Long userId) throws EntityNotFoundException {

        Deal deal = dealRepository.findById(dealDto.getId()).orElseThrow(EntityNotFoundException::new);

        deal.setName(dealDto.getName());
        deal.setDescription(dealDto.getDescription());
        deal.setStage(dealDto.getStageId());

        return objectMapper.mapFrom(dealRepository.save(deal), Optional.of(dealDto.getOrderList()).orElse(new ArrayList<>()));
    }

    @Override
    public List<DealDto> fetchByUser(Long userId) {

        List<DealDto> dealDtoList = new ArrayList<>();

        List<Deal> deals = dealRepository.findAllByUserId(userId).orElse(new ArrayList<>());

        deals.forEach(item->{
            List<Order> orders = orderRepository.findAllByDealId(item.getId()).orElse(new ArrayList<>());

            List<OrderDto> orderDtoList = orders.stream().map(order->objectMapper.mapFrom(order)).toList();

            dealDtoList.add(objectMapper.mapFrom(item, orderDtoList));

        });

        return dealDtoList;
    }
}
