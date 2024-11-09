package com.wisecrm.stagesservice.repository;


import com.wisecrm.stagesservice.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import java.util.*;

public interface StageRepository extends JpaRepository<Stage, Long> {


    @Query("""

select s 
from Stage s 
join Group g on g.id = s.id and g.id= :groupId

""")
    Optional<List<Stage>> findAllByGroupId(@Param("groupId") Long groupId);

    @Query("""

select s 
from Stage s 
join Group g on g.id= :groupId and g.id= :companyId and s.id= :stageId

""")
    Optional<Stage> findByGroupAndCompany(@Param("groupId") Long groupId,@Param("companyId") Long companyId,@Param("stageId") Long stageId);


    @Query("""

select s 
from Stage s 
join Group g on g.company= :companyId and g.id= s.group.id

""")
    List<Stage> findAllByCompany(@Param("companyId")Long companyId);
}
