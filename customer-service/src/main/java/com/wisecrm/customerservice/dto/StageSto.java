package com.wisecrm.customerservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
public class StageSto {
    @NotNull
    @Min(value = 1)
    private Long id;
}
