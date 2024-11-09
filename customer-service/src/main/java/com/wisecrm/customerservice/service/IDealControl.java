package com.wisecrm.customerservice.service;

import com.wisecrm.customerservice.dto.*;
import com.wisecrm.customerservice.entity.*;

import java.util.*;

public interface IDealControl {
    DealDto createDealByUserId(DealDto dto, Long userId, Customer customer);

    DealDto update(DealDto dealDto, Long userId);

    List<DealDto> fetchByUser(Long userId);
}
