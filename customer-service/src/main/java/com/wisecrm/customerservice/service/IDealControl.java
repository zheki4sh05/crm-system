package com.wisecrm.customerservice.service;

import com.wisecrm.customerservice.dto.*;
import com.wisecrm.customerservice.entity.*;

public interface IDealControl {
    DealDto createDealByUserId(DealDto dto, Long userId, Customer customer);

    DealDto update(DealDto dealDto, Long userId);
}
