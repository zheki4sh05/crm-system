package com.wisecrm.stagesservice.mapper.impl;

import com.wisecrm.stagesservice.dto.*;
import com.wisecrm.stagesservice.entity.*;
import com.wisecrm.stagesservice.mapper.*;
import org.springframework.stereotype.*;

import java.time.*;

@Component
public class ObjectMapper implements IObjectMapper {

    @Override
    public StageDto mapFrom(Stage entity) {
        return StageDto.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .name(entity.getName())
                .groupId(entity.getId())
                .build();
    }
}
