package com.wisecrm.filestorage.service.impl;

import com.wisecrm.filestorage.dto.categories.*;
import com.wisecrm.filestorage.entity.*;
import com.wisecrm.filestorage.exceptions.*;
import com.wisecrm.filestorage.repository.*;
import com.wisecrm.filestorage.service.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CategoriesControlImpl implements ICategoriesControl {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public List<CategoryDto> fetch(Long company){
       List<Category> categoryList =  categoriesRepository.findAllByCompanyId(company);

        return mapFromToDto(categoryList);
    }

    private List<CategoryDto> mapFromToDto(List<Category> categoryList) {

        var list = new ArrayList<CategoryDto>();

        categoryList.forEach(item->{

            list.add(doMapping(item));

        });

        return list;

    }

    private CategoryDto doMapping(Category category){
      return  CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .company(category.getCompany())
                .build();
    }

    @Override
    public CategoryDto create(CreateCategoryRequest categoryRequest) throws SuchCategoryAlreadyExists {

        Category category = categoriesRepository.getIfExists(categoryRequest.getId(), categoryRequest.getName());

        if (category == null) {

            category = Category.builder()
                    .name(categoryRequest.getName())
                    .company(categoryRequest.getId())
                    .build();

            category = categoriesRepository.save(category);

        } else {
            throw new SuchCategoryAlreadyExists();
        }

        return doMapping(category);
    }

    @Override
    public CategoryDto update(CreateCategoryRequest categoryRequest)throws SuchCategoryNotExists {

        Category category = categoriesRepository.findById(categoryRequest.getId()).orElseThrow(SuchCategoryNotExists::new);

        category.setName(categoryRequest.getName());

        return doMapping(categoriesRepository.save(category));
    }

    @Override
    public void delete(Long id) throws SuchCategoryNotExists {

        Category category = categoriesRepository.findById(id).orElseThrow(SuchCategoryNotExists::new);
        categoriesRepository.delete(category);
    }


}
