package com.wisecrm.employeeservice.services.impl;

import com.wisecrm.employeeservice.dto.*;
import com.wisecrm.employeeservice.entity.Task;
import com.wisecrm.employeeservice.repository.*;
import com.wisecrm.employeeservice.services.interfaces.*;
import com.wisecrm.employeeservice.util.mapper.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import java.sql.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

@Service
public class TaskControlImpl implements ITaskControl {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private IObjectMapper objectMapper;
    @Override
    public TaskDto createTask(TaskDto taskDto, Long userId) {


        Task task = Task.builder()
                .name(taskDto.getName())
                .deal(taskDto.getDealId())
                .start(Timestamp.valueOf(LocalDateTime.now()))
                .finishAt(Timestamp.valueOf(taskDto.getFinishAt().toString()))
                .build();

        task = taskRepository.save(task);

        return objectMapper.mapFrom(task);
    }

    @Override
    public List<TaskDto> fetch(Long userId) throws EntityNotFoundException {

        List<Task> taskList  = taskRepository.findAllByUserId(userId).orElse(new ArrayList<>());

        return taskList.stream().map(item->objectMapper.mapFrom(item)).collect(Collectors.toList());
    }
}
