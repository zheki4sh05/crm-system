package com.wisecrm.customerservice.service;

import com.wisecrm.customerservice.dto.*;

import java.util.*;

public interface IDealTypeControl {
    DealTypeDto create(DealTypeDto dto, Long companyId);

    List<DealTypeDto> fetchBycompanyId(Long companyId);
}
