package com.wisecrm.stagesservice.service.impl;

import com.wisecrm.stagesservice.dto.*;
import com.wisecrm.stagesservice.entity.*;
import com.wisecrm.stagesservice.exceptions.*;
import com.wisecrm.stagesservice.mapper.*;
import com.wisecrm.stagesservice.repository.*;
import com.wisecrm.stagesservice.service.*;
import jakarta.persistence.*;
import jakarta.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class StageControlImpl implements IStageControl {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private IObjectMapper objectMapper;

    private final int MAX_COUNT = 10;

    @Transactional
    @Override
    public StageDto create(StageDto stageDto) throws SuchEntityAlreadyExists, EntityNotFoundException, MaxCapacityException {


        Group group = groupRepository.findByCompanyIdAndGroup(stageDto.getGroupId(), stageDto.getCompanyId()).orElseThrow(EntityNotFoundException::new);
        Stage stage;

        TreeSet<Stage> treeSet = new TreeSet<>(stageRepository.findAllByGroupId(group.getId()).orElse(new ArrayList<>()));

        if(treeSet.size()<=MAX_COUNT){

            int order = treeSet.last().getOrder();

             stage = Stage.builder()
                    .name(stageDto.getName())
                    .description(stageDto.getDescription())
                    .group(group)
                     .order(++order)
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

        Group group = groupRepository.findByCompanyIdAndGroup(groupId, companyId).orElseThrow(EntityNotFoundException::new);

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
    public StageDto update(StageDto stageDto) throws SuchEntityAlreadyExists, EntityNotFoundException {

        Stage updateStage = stageRepository.findByGroupAndCompany(stageDto.getGroupId(), stageDto.getCompanyId(), stageDto.getId()).orElseThrow(EntityExistsException::new);
        updateStage.setName(stageDto.getName());
        try {
            updateStage = stageRepository.save(updateStage);
        } catch (DataIntegrityViolationException e) {
            throw new SuchEntityAlreadyExists();
        }

        return mapFromEntityToDto(updateStage, stageDto.getGroupId());
    }

    @Override
    public List<StageDto> fetch(Long companyId) {

        List<Stage> stageList = stageRepository.findAllByCompany(companyId);

        return stageList.stream().map(item->objectMapper.mapFrom(item)).collect(Collectors.toList());
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
