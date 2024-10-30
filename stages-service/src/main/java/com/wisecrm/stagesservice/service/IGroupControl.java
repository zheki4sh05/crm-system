package com.wisecrm.stagesservice.service;

import java.util.*;

public interface IGroupControl{
        GroupDto create(GroupDto groupDto);

        List<GroupDto> fetch(Long companyId);

        void delete(Long companyId, Long groupId);

        GroupDto update(GroupDto groupDto);
}
