package com.wisecrm.filestorage.service.impl;

import com.wisecrm.filestorage.dto.categories.*;
import com.wisecrm.filestorage.entity.*;
import com.wisecrm.filestorage.exceptions.*;
import com.wisecrm.filestorage.repository.*;
import lombok.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
class CategoriesControlImplTest {

    @Mock
    private CategoriesRepository categoriesRepository;

    @InjectMocks
    private CategoriesControlImpl categoriesControl;

    @Test
    void fetch_list_and_return_dto() {

        Long companyId = 1L;

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category());
        categoryList.add(new Category());

        doReturn(categoryList).when(categoriesRepository).findAllByCompanyId(companyId);

        List<CategoryDto> categoryDtos = categoriesControl.fetch(companyId);

        assertEquals(categoryDtos.size(), categoryList.size());

    }

    @Test
    @SneakyThrows
    void create_category_and_not_throw_exception() {

        Long companyId = 1L;

        Category category = Category.builder()
                .id(1L)
                .name("Категория")
                .company(companyId)
                .build();

        Category savedCategory = Category.builder()
                .name("Категория")
                .company(companyId)
                .build();

        CreateCategoryRequest categoryRequest = CreateCategoryRequest.builder()
                .id(companyId)
                .name(category.getName())
                .build();

        doReturn(null).when(categoriesRepository).getIfExists(companyId,category.getName());
        doReturn(category).when(categoriesRepository).save(savedCategory);

        CategoryDto categoryDto = categoriesControl.create(categoryRequest);

        assertDoesNotThrow(SuchCategoryAlreadyExists::new);
        assertEquals(categoryDto.getId(), category.getId());
        assertEquals(categoryDto.getName(), category.getName());
        assertEquals(categoryDto.getCompany(), category.getCompany());


    }

    @Test
    @SneakyThrows
    void try_to_create_category_and_throw_exception() {
        Long companyId = 1L;

        Category category = Category.builder()
                .id(1L)
                .name("Категория")
                .company(companyId)
                .build();

        CreateCategoryRequest categoryRequest = CreateCategoryRequest.builder()
                .id(companyId)
                .name(category.getName())
                .build();

        doReturn(category).when(categoriesRepository).getIfExists(companyId,category.getName());

        assertThrows(SuchCategoryAlreadyExists.class, ()->{
            categoriesControl.create(categoryRequest);
        });
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}