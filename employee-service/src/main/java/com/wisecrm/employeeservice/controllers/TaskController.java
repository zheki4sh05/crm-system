package com.wisecrm.employeeservice.controllers;

import com.wisecrm.employeeservice.dto.*;
import com.wisecrm.employeeservice.exceptions.*;
import com.wisecrm.employeeservice.services.interfaces.*;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name = "task controller")
@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired
    private ITaskControl taskControl;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody TaskDto taskDto) {

            Long userId = 2L;

            TaskDto createdTask =  taskControl.createTask(taskDto, userId);

            return new ResponseEntity<>(createdTask, HttpStatus.CREATED);

    }

    @GetMapping("/create")
    public ResponseEntity<?> fetch() {

        Long userId = 2L;

        List<TaskDto> taskDtoList =  taskControl.fetch(userId);

        return new ResponseEntity<>(taskDtoList, HttpStatus.OK);

    }

}
