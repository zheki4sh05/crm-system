package com.wisecrm.customerservice.controllers;

import com.wisecrm.customerservice.dto.*;
import com.wisecrm.customerservice.facade.*;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name = "deal type controller")
@RestController
@RequestMapping("/api/v1/dealtype")
public class DealTypeController {

    @Autowired
    private IAuthUserDealControlFacade authUserDealControlFacade;


    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody DealTypeDto dto) {


        Long userId = 1l;

        DealTypeDto createdDealDto = authUserDealControlFacade.createDealType(dto,userId);

        return new ResponseEntity<>(createdDealDto, HttpStatus.CREATED);

    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetch() {

        Long userId = 1l;

        List<DealTypeDto> dealTypeDtos = authUserDealControlFacade.fetchDealTypes(userId);

        return new ResponseEntity<>(dealTypeDtos, HttpStatus.CREATED);

    }

}
