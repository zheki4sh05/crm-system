package com.wisecrm.crmapiservice.repository;

import com.wisecrm.crmapiservice.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import java.util.*;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

   @Query("""

select c 
from Customer c 
where c.email= :email

""")
   Optional<Customer> getByEmail(@Param("email") String email);
}
