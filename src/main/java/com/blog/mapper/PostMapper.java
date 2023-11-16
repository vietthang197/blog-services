package com.blog.mapper;

import com.blog.dto.PostDto;
import com.blog.entity.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDto toDto(Post post);
    List<PostDto> toListDto(List<Post> posts);
}
