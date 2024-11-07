package com.wisecrm.employeeservice.repository;


import com.wisecrm.employeeservice.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("""

select u 
from User u 
where u.id= :userId

""")
    User findByUserId(@Param("userId")Long userId);
}
