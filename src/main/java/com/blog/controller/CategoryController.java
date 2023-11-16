package com.blog.controller;

import com.blog.dto.BasicResponseDto;
import com.blog.dto.CategoryDto;
import com.blog.services.CategoryService;
import com.blog.services.vm.CreateCategoryVM;
import com.blog.services.vm.UpdateCategoryVM;
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
