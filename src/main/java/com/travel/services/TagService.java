package com.travel.services;

import com.travel.dto.BasicResponseDto;
import com.travel.dto.TagDto;
import com.travel.services.vm.CreateTagVM;
import com.travel.services.vm.UpdateTagVM;

import java.util.List;

public interface TagService {
    BasicResponseDto<List<TagDto>> findAll();

    BasicResponseDto<TagDto> create(CreateTagVM createTagVM);

    BasicResponseDto<TagDto> update(UpdateTagVM updateTagVM);
}
