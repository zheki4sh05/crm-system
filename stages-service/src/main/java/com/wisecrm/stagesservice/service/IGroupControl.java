package com.wisecrm.stagesservice.service;


import com.wisecrm.stagesservice.dto.*;
import com.wisecrm.stagesservice.exceptions.*;
import jakarta.persistence.*;

import java.util.*;

public interface IGroupControl{
        GroupDto create(GroupDto groupDto);

        List<GroupDto> fetch(Long companyId);

        void delete(Long companyId, Long groupId) throws EntityNotFoundException;

        GroupDto update(GroupDto groupDto) throws SuchEntityAlreadyExists;
}
