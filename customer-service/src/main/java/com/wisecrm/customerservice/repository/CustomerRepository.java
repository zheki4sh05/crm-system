package com.wisecrm.customerservice.repository;

import com.wisecrm.customerservice.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("""

select c 
from Customer c 
where c.email= :email

""")
    Optional<Customer> findByEmail(@Value("email") String email);
}
