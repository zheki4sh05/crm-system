package com.wisecrm.customerservice.dto;

import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DealDto {

    private Long id;
    private String name;
    private String description;
    private Long stageId;
    private String started;
    private CustomerDto customerDto;
    private List<OrderDto> orderList;


}
