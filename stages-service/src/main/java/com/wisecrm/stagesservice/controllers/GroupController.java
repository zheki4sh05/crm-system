package com.wisecrm.stagesservice.controllers;

import com.wisecrm.stagesservice.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name = "group controller")
@RestController
@RequestMapping("/api/v1/group")
public class GroupController {

    @Autowired
    private IGroupControl groupControl;

    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@Valid @RequestBody GroupDto groupDto) {
        try {

            GroupDto createdGroup  = groupControl.create(groupDto);

            return new ResponseEntity<>(groupDto, HttpStatus.CREATED);
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

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchAll(@RequestParam Long companyId) {
        try {

            List<GroupDto> stageDtoList  = groupControl.fetch(companyId);

            return new ResponseEntity<>(stageDtoList, HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long companyId,
                                    @RequestParam Long groupId) {
        try {

            groupControl.delete(companyId, groupId);

            return new ResponseEntity<>("", HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody GroupDto groupDto) {
        try {

            GroupDto updatedDto = groupControl.update(groupDto);

            return new ResponseEntity<>(updatedDto, HttpStatus.OK);
        }
        catch (SuchEntityAlreadyExists e) {
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