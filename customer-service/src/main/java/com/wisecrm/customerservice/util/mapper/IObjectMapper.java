package com.wisecrm.customerservice.util.mapper;

import com.wisecrm.customerservice.dto.*;
import com.wisecrm.customerservice.entity.*;

import java.util.*;

public interface IObjectMapper {
    DealDto mapFrom(Deal save, List<OrderDto> orderDtoList);

    CustomerDto mapFrom(Customer customer);

    DealTypeDto mapFrom(DealType dealType);


    OrderDto mapFrom(Order order);
}
