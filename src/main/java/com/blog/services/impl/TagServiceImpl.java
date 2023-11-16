package com.blog.services.impl;

import com.blog.constants.Constant;
import com.blog.dto.BasicResponseDto;
import com.blog.dto.TagDto;
import com.blog.entity.Tag;
import com.blog.mapper.TagMapper;
import com.blog.repository.TagRepository;
import com.blog.services.TagService;
import com.blog.services.vm.CreateTagVM;
import com.blog.services.vm.UpdateTagVM;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagMapper mapper;

    @Override
    public BasicResponseDto<List<TagDto>> findAll() {
        return BasicResponseDto.ok(mapper.toListDto(tagRepository.findAll()));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public BasicResponseDto<TagDto> create(CreateTagVM createTagVM) {
        Tag tag = Tag.builder()
                .name(createTagVM.getName())
                .isDeleted(Constant.STR_N)
                .build();
        tagRepository.save(tag);
        return BasicResponseDto.ok(mapper.toDto(tag));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public BasicResponseDto<TagDto> update(UpdateTagVM updateTagVM) {
        Optional<Tag> tagOptional = tagRepository.findById(updateTagVM.getId());
        if (tagOptional.isEmpty()) {
            return BasicResponseDto.<TagDto>builder().status(Constant.ERR_CODE.ERR_400).message("Tag not found!").data(null).build();
        }
        Tag tag = tagOptional.get();
        tag.setName(updateTagVM.getName());
        return BasicResponseDto.ok(mapper.toDto(tag));
    }
}
