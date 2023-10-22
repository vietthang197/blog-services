package com.travel.mapper;

import com.travel.dto.PostDto;
import com.travel.entity.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDto toDto(Post post);
    List<PostDto> toListDto(List<Post> posts);
}
