package com.blog.services;

import com.blog.dto.BasicResponseDto;
import com.blog.dto.TagDto;
import com.blog.services.vm.CreateTagVM;
import com.blog.services.vm.UpdateTagVM;

import java.util.List;

public interface TagService {
    BasicResponseDto<List<TagDto>> findAll();

    BasicResponseDto<TagDto> create(CreateTagVM createTagVM);

    BasicResponseDto<TagDto> update(UpdateTagVM updateTagVM);
}
