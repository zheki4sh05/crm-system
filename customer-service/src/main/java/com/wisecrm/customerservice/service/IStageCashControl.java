package com.wisecrm.customerservice.service;

import java.util.*;

public interface IStageCashControl {
    Optional<Long> findById(Long userId);

    Optional<Long> findMinOrderStage();
}
