package com.wisecrm.crmapiservice.repository;

import com.wisecrm.crmapiservice.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import java.util.*;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("""

select a 
from Application a 
where a.key.key= :key and a.key.companyId= :companyId

""")
    List<Application> findAllByApiKeyAndCompanyId(@Param("key")String key,@Param("companyId") Long companyId);
}
