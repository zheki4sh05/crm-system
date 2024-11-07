package com.wisecrm.customerservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private Long id;
    private String email;
    private String name;
    private String lastname;
    private String address;

}
