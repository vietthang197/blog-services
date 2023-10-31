package com.travel.services.impl;

import com.travel.constants.Constant;
import com.travel.dto.BasicResponseDto;
import com.travel.dto.CategoryDto;
import com.travel.entity.Category;
import com.travel.mapper.CategoryMapper;
import com.travel.repository.CategoryRepository;
import com.travel.services.CategoryService;
import com.travel.services.vm.CreateCategoryVM;
import com.travel.services.vm.UpdateCategoryVM;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper mapper;

    @Override
    public BasicResponseDto<List<CategoryDto>> findAll() {
        return BasicResponseDto.ok(mapper.toListDto(categoryRepository.findAll()));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public BasicResponseDto<CategoryDto> create(CreateCategoryVM createCategoryVM) {
        Category category = Category.builder()
                .name(createCategoryVM.getName())
                .isDeleted(Constant.STR_N)
                .build();
        categoryRepository.save(category);
        return BasicResponseDto.ok(mapper.toDto(category));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public BasicResponseDto<CategoryDto> update(UpdateCategoryVM updateCategoryVM) {
        Optional<Category> categoryOptional = categoryRepository.findById(updateCategoryVM.getId());
        if (categoryOptional.isEmpty()) {
            return BasicResponseDto.<CategoryDto>builder().status(Constant.ERR_CODE.ERR_400).message("Category not found!").data(null).build();
        }
        Category category = categoryOptional.get();
        category.setName(updateCategoryVM.getName());
        return BasicResponseDto.ok(mapper.toDto(category));
    }
}
