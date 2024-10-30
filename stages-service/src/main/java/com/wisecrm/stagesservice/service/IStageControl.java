package com.wisecrm.stagesservice.service;

import java.util.*;

public interface IStageControl {
    StageDto create(StageDto stageDto) throws SuchEntityAlreadyExists, EntityNotFoundException;

    List<StageDto> fetch(Long companyId, Long groupId) throws EntityNotFoundException;

    void delete(Long companyId, Long groupId, Long stageId) throws EntityNotFoundException;

    StageDto update(StageDto stageDto, String part) throws SuchEntityAlreadyExists, EntityNotFoundException;
}
