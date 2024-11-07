package com.wisecrm.customerservice.repository;

import com.wisecrm.customerservice.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface DealTypeRepository extends JpaRepository<DealType, Long> {

    @Query("""

select dt 
from DealType dt 
where dt.name= :name and dt.company= :companyId

""")
    Optional<DealType> findByCompanyAndName(@Param("name") String name,@Param("companyId") Long companyId);

   Optional<List<DealType>> findByCompanyId(Long companyId);
}
