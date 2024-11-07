package com.wisecrm.employeeservice.util.mapper;

import com.wisecrm.employeeservice.dto.*;
import com.wisecrm.employeeservice.entity.*;

public interface IObjectMapper {
    CompanyDto mapFrom(Company company);
    TaskDto mapFrom(Task task);
}
