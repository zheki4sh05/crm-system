package com.wisecrm.customerservice.service.impl;

import com.wisecrm.customerservice.dto.*;
import com.wisecrm.customerservice.entity.*;
import com.wisecrm.customerservice.exceptions.*;
import com.wisecrm.customerservice.repository.*;
import com.wisecrm.customerservice.service.*;
import jakarta.persistence.*;
import jakarta.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class StageControlImpl implements IStageControl {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StageRepository stageRepository;

    private final int MAX_COUNT = 10;

    @Transactional
    @Override
    public StageDto create(StageDto stageDto) throws SuchEntityAlreadyExists, EntityNotFoundException, MaxCapacityException {


        Group group = groupRepository.findById(stageDto.getGroupId(), stageDto.getCompanyId()).orElseThrow(EntityNotFoundException::new);
        Stage stage;

        int countById = stageRepository.findAllByGroupId(group.getId()).orElse(new ArrayList<>()).size();

        if(countById<=MAX_COUNT){

             stage = Stage.builder()
                    .name(stageDto.getName())
                    .description(stageDto.getDescription())
                    .group(group)
                    .build();
            try {
                stage = stageRepository.save(stage);
            } catch (DataIntegrityViolationException e) {
                throw new SuchEntityAlreadyExists();
            }
        }else{
            throw new MaxCapacityException();
        }

        return mapFromEntityToDto(stage, stageDto.getCompanyId());
    }

    @Override
    public List<StageDto> fetch(Long companyId, Long groupId) throws EntityNotFoundException {

        Group group = groupRepository.findById(groupId, companyId).orElseThrow(EntityNotFoundException::new);

        List<Stage> stageList = stageRepository.findAllByGroupId(groupId).orElse(new ArrayList<>());

        List<StageDto> stageDtoList = new ArrayList<>();

        stageList.forEach(item->{
            stageDtoList.add(mapFromEntityToDto(item,groupId));
        });

        return stageDtoList;
    }


    @Override
    @Transactional
    public void delete(Long companyId, Long groupId, Long stageId) throws EntityNotFoundException {

        Stage deleteStage = stageRepository.findByGroupAndCompany(groupId, companyId, stageId).orElseThrow(EntityNotFoundException::new);
        stageRepository.delete(deleteStage);

    }

    @Override
    @Transactional
    public StageDto update(StageDto stageDto, String part) throws SuchEntityAlreadyExists, EntityNotFoundException {

        Stage updateStage = stageRepository.findByGroupAndCompany(stageDto.getGroupId(), stageDto.getCompanyId(), stageDto.getId()).orElseThrow(EntityExistsException::new);
        updateStage.setName(stageDto.getName());
        try {
            updateStage = stageRepository.save(updateStage);
        } catch (DataIntegrityViolationException e) {
            throw new SuchEntityAlreadyExists();
        }

        return mapFromEntityToDto(updateStage, stageDto.getGroupId());
    }

    private StageDto mapFromEntityToDto(Stage entity, Long companyId){

        return StageDto.builder()
                .id(entity.getId())
                .companyId(companyId)
                .description(entity.getDescription())
                .name(entity.getName())
                .groupId(entity.getId())
                .build();
    }
}
