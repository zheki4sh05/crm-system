package com.wisecrm.stagesservice.repository;

import java.util.*;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findById(Long groupId, Long companyId);

}
