package com.wisecrm.stagesservice.controllers;


import com.wisecrm.stagesservice.dto.*;
import com.wisecrm.stagesservice.exceptions.*;
import com.wisecrm.stagesservice.service.*;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name = "stage controller")
@RestController
@RequestMapping("/api/v1/stage")
public class StageController {

    @Autowired
    private IStageControl stageControl;
    @PostMapping("/create")
    public ResponseEntity<?> createStage(@Valid @RequestBody StageDto stageDto) {
        try {

            StageDto createdStageDto  = stageControl.create(stageDto);

            return new ResponseEntity<>(createdStageDto, HttpStatus.CREATED);
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
    public ResponseEntity<?> fetchAll(@RequestParam Long companyId,
                                         @RequestParam Long groupId) {
        try {

            List<StageDto> stageDtoList  = stageControl.fetch(companyId, groupId);

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
                                      @RequestParam Long groupId,
                                      @RequestParam Long stageId ) {
        try {

            stageControl.delete(companyId, groupId,stageId);

            return new ResponseEntity<>("", HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{part}")
    public ResponseEntity<?> update(@RequestBody StageDto stageDto, @PathVariable String part) {
        try {

         StageDto updatedSto = stageControl.update(stageDto, part);

            return new ResponseEntity<>(updatedSto, HttpStatus.OK);
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
