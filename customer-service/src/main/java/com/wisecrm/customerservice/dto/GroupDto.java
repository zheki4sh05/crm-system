package com.wisecrm.customerservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    @NotNull(message = "group id can't be be null")
    private Long id;

    @NotNull
    @NotBlank(message = "group name can't be blank")
    @Size(min = 1, max = 30, message = "string size: min 1 nad max 30")
    private String name;

    @Size(min = 1, max = 255, message = "string size: min 1 nad max 255")
    private String description;

    @NotNull
    private Long companyId;

    @NotNull
    @Size(min = 3, max = 3, message = "correct length:3")
    private String customerType;

}
