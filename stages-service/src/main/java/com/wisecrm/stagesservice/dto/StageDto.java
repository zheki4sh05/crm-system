package com.wisecrm.stagesservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StageDto {

    @NotNull
    private Long id;

    @NotNull
    @NotBlank(message = "Stage name must not be blank")
    @Size(min = 1, max = 30, message = "string size: min 1 nad max 30")
    private String name;

    @Size(min = 1, max = 255, message = "string size: min 1 nad max 255")
    private String description;

    @NotNull(message = "Must be a company id")
    private Long companyId;

    @NotNull(message = "Must be a group id")
    private Long groupId;
}
