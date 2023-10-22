package com.travel.controller;

import com.travel.dto.BasicResponseDto;
import com.travel.dto.TagDto;
import com.travel.services.TagService;
import com.travel.services.vm.CreateTagVM;
import com.travel.services.vm.UpdateTagVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping
    public BasicResponseDto<List<TagDto>> getListTag() {
        return tagService.findAll();
    }

    @PostMapping
    public BasicResponseDto<TagDto> createTag(@RequestBody CreateTagVM createTagVM) {
        return tagService.create(createTagVM);
    }

    @PutMapping
    public BasicResponseDto<TagDto> updateTag(@RequestBody UpdateTagVM updateTagVM) {
        return tagService.update(updateTagVM);
    }
}
