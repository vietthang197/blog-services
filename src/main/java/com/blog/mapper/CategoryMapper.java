package com.blog.mapper;

import com.blog.dto.CategoryDto;
import com.blog.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    List<CategoryDto> toListDto(List<Category> categoryList);
}
