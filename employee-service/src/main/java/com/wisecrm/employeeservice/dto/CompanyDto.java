package com.wisecrm.employeeservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {

    private Long id;

    @NotNull
    @NotBlank
    private String name;

    private String description;


}
