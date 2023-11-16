package com.blog.services;

import com.blog.dto.BasicResponseDto;
import com.blog.dto.PostDto;
import com.blog.services.vm.CreatePostVM;
import com.blog.services.vm.UpdatePostVM;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface PostService {
    BasicResponseDto<PostDto> create(CreatePostVM createPostVM);

    @EntityGraph(attributePaths = {""})
    BasicResponseDto<List<PostDto>> findByCategory(String category);

    BasicResponseDto<PostDto> update(UpdatePostVM updatePostVM);

    BasicResponseDto<Void> deletePost(UpdatePostVM updatePostVM);
}
