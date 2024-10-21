package com.wisecrm.filestorage.repository;

import com.wisecrm.filestorage.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface ExctensionRepository extends JpaRepository<Extension, Long> {
    Optional<Extension> findByName(String s);
}
