package com.travel.controller;

import com.travel.dto.BasicResponseDto;
import com.travel.dto.CategoryDto;
import com.travel.services.CategoryService;
import com.travel.services.vm.CreateCategoryVM;
import com.travel.services.vm.UpdateCategoryVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public BasicResponseDto<List<CategoryDto>> getListCategory() {
        return categoryService.findAll();
    }

    @PostMapping
    public BasicResponseDto<CategoryDto> createCategory(@RequestBody CreateCategoryVM createCategoryVM) {
        return categoryService.create(createCategoryVM);
    }

    @PutMapping
    public BasicResponseDto<CategoryDto> updateCategory(@RequestBody UpdateCategoryVM updateCategoryVM) {
        return categoryService.update(updateCategoryVM);
    }
}
