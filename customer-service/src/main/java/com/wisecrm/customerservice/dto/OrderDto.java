package com.wisecrm.customerservice.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto {

    private Long id;
    private String name;
    private Integer count;
    private String code;
    private Long dealId;
    private Double price;

}
