package com.wisecrm.employeeservice.repository;

import com.wisecrm.employeeservice.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import java.util.*;

public interface CompanyRepository extends JpaRepository<Company, Long> {


    @Query("""

select c 
from Company c 
where c.name= :name

""")
    Optional<Company> findByName(@Param("name") String name);
}
