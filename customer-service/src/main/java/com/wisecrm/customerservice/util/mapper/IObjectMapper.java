package com.wisecrm.customerservice.util.mapper;

import com.wisecrm.customerservice.dto.*;
import com.wisecrm.customerservice.entity.*;

public interface IObjectMapper {
    DealDto mapFrom(Deal save);

    CustomerDto mapFrom(Customer customer);

    DealTypeDto mapFrom(DealType dealType);
}
