package com.wisecrm.customerservice.controllers;

import com.wisecrm.customerservice.dto.*;
import com.wisecrm.customerservice.exceptions.*;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Tag(name = "deal controller")
@RestController
@RequestMapping("/api/v1/deal")
public class DealController {

    @Autowired
    private IDealControl dealControl;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody DealDto dto) {
        try {

            DealDto createdDealDto  = dealControl.create(dto);

            return new ResponseEntity<>(createdDealDto, HttpStatus.CREATED);
        } catch (SuchEntityAlreadyExists e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
