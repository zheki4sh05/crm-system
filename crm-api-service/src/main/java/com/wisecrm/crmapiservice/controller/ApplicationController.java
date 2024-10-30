package com.wisecrm.crmapiservice.controller;

import com.wisecrm.crmapiservice.dto.*;
import com.wisecrm.crmapiservice.facade.*;
import com.wisecrm.crmapiservice.service.*;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name = "application controller")
@RestController
@RequestMapping("/api/v1/application")
@SecurityRequirement(name="bearerAuth")
public class ApplicationController {

    @Autowired
    private IAuthenticatedClientDataFacade authenticatedClientDataFacade;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ApplicationDto applicationDto) {
        try {

            authenticatedClientDataFacade.create(applicationDto);

            return new ResponseEntity<>("", HttpStatus.CREATED);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<?> fetchAll(@RequestParam String apiKey, @RequestParam Long companyId) {
        try {

          List<ApplicationDto> applicationDtoList =   authenticatedClientDataFacade.getAllByApiKey(apiKey,companyId);

            return new ResponseEntity<>(applicationDtoList, HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
