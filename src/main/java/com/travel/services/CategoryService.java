package com.travel.services;

import com.travel.dto.BasicResponseDto;
import com.travel.dto.CategoryDto;
import com.travel.services.vm.CreateCategoryVM;
import com.travel.services.vm.UpdateCategoryVM;

import java.util.List;

public interface CategoryService {
    BasicResponseDto<List<CategoryDto>> findAll();

    BasicResponseDto<CategoryDto> create(CreateCategoryVM createCategoryVM);

    BasicResponseDto<CategoryDto> update(UpdateCategoryVM updateCategoryVM);
}
