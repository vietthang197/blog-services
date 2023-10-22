package com.travel.services;

import com.travel.dto.BasicResponseDto;
import com.travel.dto.PostDto;
import com.travel.services.vm.CreatePostVM;

import java.util.List;

public interface PostService {
    BasicResponseDto<PostDto> create(CreatePostVM createPostVM);

    BasicResponseDto<List<PostDto>> findByCategory(String category);
}
