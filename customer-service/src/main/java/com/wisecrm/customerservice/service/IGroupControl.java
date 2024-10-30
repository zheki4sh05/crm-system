package com.wisecrm.customerservice.service;

import com.wisecrm.customerservice.dto.*;

import java.util.*;

public interface IGroupControl{
        GroupDto create(GroupDto groupDto);

        List<GroupDto> fetch(Long companyId);

        void delete(Long companyId, Long groupId);

        GroupDto update(GroupDto groupDto);
}
