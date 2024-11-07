package com.wisecrm.customerservice.facade;

import com.wisecrm.customerservice.dto.*;

import java.util.*;

public interface IAuthUserDealControlFacade {
    DealDto createDealByUserId(DealDto dto, Long userId);

    DealDto updateDeal(DealDto dealDto, Long userId);

    DealTypeDto createDealType(DealTypeDto dto, Long userId);

    List<DealTypeDto> fetchDealTypes(Long userId);
}
