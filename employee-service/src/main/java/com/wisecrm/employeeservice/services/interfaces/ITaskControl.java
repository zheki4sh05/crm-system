package com.wisecrm.employeeservice.services.interfaces;

import com.wisecrm.employeeservice.dto.*;

import java.util.*;

public interface ITaskControl {
    TaskDto createTask(TaskDto taskDto, Long userId);

    List<TaskDto> fetch(Long userId);
}
