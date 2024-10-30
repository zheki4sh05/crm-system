package com.wisecrm.employeeservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private Long id;
    private String name;
    private String description;

}
