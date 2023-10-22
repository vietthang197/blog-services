package com.travel.mapper;

import com.travel.dto.CategoryDto;
import com.travel.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    List<CategoryDto> toListDto(List<Category> categoryList);
}
