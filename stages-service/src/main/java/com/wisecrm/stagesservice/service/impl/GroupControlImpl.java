package com.wisecrm.stagesservice.service.impl;

import com.wisecrm.stagesservice.dto.*;
import com.wisecrm.stagesservice.entity.*;
import com.wisecrm.stagesservice.exceptions.*;
import com.wisecrm.stagesservice.repository.*;
import com.wisecrm.stagesservice.service.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;


@Service
public class GroupControlImpl implements IGroupControl {

    @Autowired
    private GroupRepository groupRepository;


    private GroupDto mapFrom(Group group){
        return GroupDto.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .companyId(group.getCompany())
                .build();
    }
    @Override
    public GroupDto create(GroupDto groupDto) {

        if(groupRepository.findByCompanyIdAndName(groupDto.getCompanyId(),groupDto.getName()).isEmpty()){
            Group group = Group.builder()
                .name(groupDto.getName())
                .company(groupDto.getCompanyId())
                    .description(groupDto.getDescription())
                .build();

            group=  groupRepository.save(group);

            return mapFrom(group);

        }else{
            throw new SuchEntityAlreadyExists("Group with name '"+groupDto.getName()+"' already exists!");
        }

    }

    @Override
    public List<GroupDto> fetch(Long companyId) {

        List<Group> groups = groupRepository.findByCompanyId(companyId).orElse(new ArrayList<Group>());

        return groups.stream().map(this::mapFrom).collect(Collectors.toList());
    }

    @Override
    public void delete(Long companyId, Long groupId) throws EntityNotFoundException {
        Group deleteGroup = groupRepository.findByCompanyIdAndGroup(companyId, groupId).orElseThrow(EntityNotFoundException::new);

        groupRepository.delete(deleteGroup);
    }

    @Override
    public GroupDto update(GroupDto groupDto) throws SuchEntityAlreadyExists {

        Group group = Group.builder()
                .id(groupDto.getId())
                .company(groupDto.getCompanyId())
                .name(groupDto.getName())
                .description(groupDto.getDescription())
                .build();
        try{
            group=   groupRepository.save(group);
        }catch (DataIntegrityViolationException e){
            throw new SuchEntityAlreadyExists("Group with name '"+groupDto.getName()+"' already exists!");
        }


        return mapFrom(group);
    }
}
