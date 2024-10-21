package com.wisecrm.filestorage.controller;

import com.wisecrm.filestorage.dto.categories.*;
import com.wisecrm.filestorage.exceptions.*;
import com.wisecrm.filestorage.service.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController(value = "documents/categories/")
public class CategoriesController {

    @Autowired
    private ICategoriesControl categoriesControl;

    @GetMapping("/fetch/{companyId}")
    public ResponseEntity<?> fetch(@RequestHeader Map<String, String> headers,
                                          @RequestParam(value = "company") Long company) {
        try {

            List<CategoryDto> categoriesList = categoriesControl.fetch(company);

            return ResponseEntity.ok(categoriesList);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestHeader Map<String, String> headers,
                                          @RequestBody CreateCategoryRequest categoryRequest) {
        try {

            CategoryDto categoriesList = categoriesControl.create(categoryRequest);

            return ResponseEntity.ok(categoriesList);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (SuchCategoryAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

    @PutMapping ("/update")
    public ResponseEntity<?> update(@RequestHeader Map<String, String> headers,
                                          @RequestBody CreateCategoryRequest categoryRequest) {
        try {

          CategoryDto categoryDto   = categoriesControl.update(categoryRequest);

            return ResponseEntity.ok(categoryDto);
        } catch (DataIntegrityViolationException | SuchCategoryNotExists e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> delete(@RequestHeader Map<String, String> headers,
                                    @PathVariable Long id) {
        try {

            categoriesControl.delete(id);

            return ResponseEntity.ok(id);
        } catch (DataIntegrityViolationException | SuchCategoryNotExists e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

}
