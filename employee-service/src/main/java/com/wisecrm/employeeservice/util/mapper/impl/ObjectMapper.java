package com.wisecrm.employeeservice.util.mapper.impl;

import com.wisecrm.employeeservice.dto.*;
import com.wisecrm.employeeservice.entity.*;
import com.wisecrm.employeeservice.util.mapper.*;
import org.springframework.stereotype.*;

import java.time.*;

@Component
public class ObjectMapper implements IObjectMapper {

    @Override
    public CompanyDto mapFrom(Company company) {
        return CompanyDto.builder()
                .name(company.getName())
                .description(company.getDescription())
                .id(company.getId())
                .build();
    }

    @Override
    public TaskDto mapFrom(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .start(LocalDate.from(task.getStart().toLocalDateTime()))
                .finishAt(LocalDate.from(task.getFinishAt().toLocalDateTime()))
                .dealId(task.getDeal())
                .build();
    }
}
