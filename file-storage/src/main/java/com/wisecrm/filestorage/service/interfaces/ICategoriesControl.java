package com.wisecrm.filestorage.service.interfaces;

import com.wisecrm.filestorage.dto.categories.*;
import com.wisecrm.filestorage.exceptions.*;

import java.util.*;

public interface ICategoriesControl {
    List<CategoryDto> fetch(Long company);

    CategoryDto create(CreateCategoryRequest categoryRequest) throws SuchCategoryAlreadyExists;

    CategoryDto update(CreateCategoryRequest categoryRequest) throws SuchCategoryNotExists;

    void delete(Long id) throws SuchCategoryNotExists;
}
