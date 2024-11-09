package com.wisecrm.customerservice.repository;

import com.wisecrm.customerservice.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import java.util.*;

public interface DealRepository extends JpaRepository<Deal, Long> {

    @Query("""

select d 
from Deal d 
where d.employee_id= :userId

""")
    Optional<List<Deal>> findAllByUserId(@Param("userId")Long userId);
}
