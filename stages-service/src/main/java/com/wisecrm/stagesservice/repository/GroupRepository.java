package com.wisecrm.stagesservice.repository;


import com.wisecrm.stagesservice.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import java.util.*;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findById(Long groupId, Long companyId);

    @Query("""
select g 
from Group g 
where g.company = :companyId and g.name = :name

""")
    Optional<Group> findByCompanyIdAndName(@Param("companyId") Long companyId, @Param("name")String name);

        @Query("""
    select g 
    from Group g 
    where g.company = :companyId
    
    """)
    Optional<ArrayList<Group>> findByCompanyId(@Param("companyId") Long companyId);

    @Query("""
    select g 
    from Group g 
    where g.company = :companyId and g.id= :groupId
    
    """)
    Optional<Group> findByCompanyIdAndGroup(@Param("companyId")Long companyId,@Param("groupId") Long groupId);
}
