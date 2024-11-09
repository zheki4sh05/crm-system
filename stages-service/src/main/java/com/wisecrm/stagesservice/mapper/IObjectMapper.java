package com.wisecrm.stagesservice.mapper;

import com.wisecrm.stagesservice.dto.*;
import com.wisecrm.stagesservice.entity.*;

public interface IObjectMapper {
    StageDto mapFrom(Stage entity);
}
