package com.wisecrm.customerservice.repository;

import com.wisecrm.customerservice.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""

select o 
from Order o 
where o.deal.id= :dealId

""")
    Optional<List<Order>> findAllByDealId(@Param("dealId") Long id);
}
