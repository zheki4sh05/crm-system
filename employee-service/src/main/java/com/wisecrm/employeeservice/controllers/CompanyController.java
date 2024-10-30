package com.wisecrm.employeeservice.controllers;

import com.wisecrm.employeeservice.dto.*;
import com.wisecrm.employeeservice.exceptions.*;
import com.wisecrm.employeeservice.services.interfaces.*;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Tag(name = "company controller")
@RestController
@RequestMapping("/api/v1/group")
public class CompanyController {

    @Autowired
    private ICompanyControl companyControl;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CompanyDto companyDto) {
        try {

            CompanyDto createdCompanyDto  = companyControl.create(companyDto);

            return new ResponseEntity<>(createdCompanyDto, HttpStatus.CREATED);
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
    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody CompanyDto companyDto) {
        try {

            CompanyDto createdCompanyDto  = companyControl.updateCompany(companyDto);

            return new ResponseEntity<>(createdCompanyDto, HttpStatus.OK);
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
    @GetMapping("/info")
    public ResponseEntity<?> fetch(@RequestParam String email) {
        try {

            CompanyDto companyDto  = companyControl.fetch(email);

            return new ResponseEntity<>(companyDto, HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/info")
    public ResponseEntity<?> key(@RequestParam Long companyId) {
        try {

            String key  = companyControl.fetchKey(companyId);

            return new ResponseEntity<>(key, HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/info")
    public ResponseEntity<?> generateKey(@RequestParam Long companyId) {
        try {

            String newKey  = companyControl.generateKey(companyId);

            return new ResponseEntity<>(newKey, HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
