package com.blog.mapper;

import com.blog.dto.TagDto;
import com.blog.entity.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDto toDto(Tag tag);
    List<TagDto> toListDto(List<Tag> tags);
}
