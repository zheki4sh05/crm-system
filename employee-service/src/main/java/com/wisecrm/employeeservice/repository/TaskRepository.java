package com.wisecrm.employeeservice.repository;

import com.wisecrm.employeeservice.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("""

select t 
from Task t 
where t.id= :id

""")
    Optional<Task> findByUserId(@Param("id") Long aLong);

    @Query("""

select t 
from Task t 
where t.worker.id= :userId

""")
    Optional<List<Task>> findAllByUserId(@Param("userId") Long userId);
}
