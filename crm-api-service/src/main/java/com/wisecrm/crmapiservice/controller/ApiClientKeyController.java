package com.wisecrm.crmapiservice.controller;

import com.wisecrm.crmapiservice.service.*;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Tag(name = "api key controller")
@RestController
@RequestMapping("/api/v1/key")
public class ApiClientKeyController {

    @Autowired
    private IApiClientKeyControl apiClientKeyControl;

    @PostMapping("/generate")
    public ResponseEntity<?> generate(Long companyId) {
        try {

            String key =  apiClientKeyControl.generate(companyId);

            return new ResponseEntity<>(key, HttpStatus.CREATED);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
