package com.wisecrm.customerservice.repository;

import com.wisecrm.customerservice.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findById(Long groupId, Long companyId);

}
