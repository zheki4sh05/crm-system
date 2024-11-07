package com.wisecrm.customerservice.service;

import com.wisecrm.customerservice.dto.*;
import com.wisecrm.customerservice.entity.*;

public interface ICustomerControl {
    Customer findOrCreate(CustomerDto dto, Long id);
}
