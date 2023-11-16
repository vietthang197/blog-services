package com.blog.services;

import com.blog.dto.BasicResponseDto;
import com.blog.dto.CategoryDto;
import com.blog.services.vm.CreateCategoryVM;
import com.blog.services.vm.UpdateCategoryVM;

import java.util.List;

public interface CategoryService {
    BasicResponseDto<List<CategoryDto>> findAll();

    BasicResponseDto<CategoryDto> create(CreateCategoryVM createCategoryVM);

    BasicResponseDto<CategoryDto> update(UpdateCategoryVM updateCategoryVM);
}
