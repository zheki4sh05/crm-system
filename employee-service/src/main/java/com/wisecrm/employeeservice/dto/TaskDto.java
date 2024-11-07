package com.wisecrm.employeeservice.dto;

import lombok.*;


import java.time.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskDto {

    private Long id;
    private String name;
    private Boolean isDone;
    private LocalDate start;
    private LocalDate finishAt;
    private Long dealId;

}
