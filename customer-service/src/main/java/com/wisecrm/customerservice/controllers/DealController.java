package com.wisecrm.customerservice.controllers;

import com.wisecrm.customerservice.dto.*;
import com.wisecrm.customerservice.facade.*;
import com.wisecrm.customerservice.service.*;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name = "deal controller")
@RestController
@RequestMapping("/api/v1/deal")
public class DealController {

    @Autowired
    private IAuthUserDealControlFacade authUserDealControlFacade;

    @Autowired
    private IDealControl dealControl;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody DealDto dto) {

            Long userId = 1l;

            DealDto createdDealDto = authUserDealControlFacade.createDealByUserId(dto,userId);

            return new ResponseEntity<>(createdDealDto, HttpStatus.CREATED);

    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@Valid DealDto dealDto) {

            Long userId = 1l;

            DealDto updatedDto = authUserDealControlFacade.updateDeal(dealDto,userId);

            return new ResponseEntity<>(updatedDto, HttpStatus.OK);

    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetch() {

        Long userId = 1l;

       List<DealDto> dealDtoList = dealControl.fetchByUser(userId);

        return new ResponseEntity<>(dealDtoList, HttpStatus.OK);

    }




}
