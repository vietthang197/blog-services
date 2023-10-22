package com.travel.mapper;

import com.travel.dto.TagDto;
import com.travel.entity.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDto toDto(Tag tag);
    List<TagDto> toListDto(List<Tag> tags);
}
