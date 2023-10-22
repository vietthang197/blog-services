package com.travel.controller;

import com.travel.dto.BasicResponseDto;
import com.travel.dto.PostDto;
import com.travel.services.PostService;
import com.travel.services.vm.CreatePostVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public BasicResponseDto<PostDto> createPost(@RequestBody CreatePostVM createPostVM) {
        return postService.create(createPostVM);
    }

    @GetMapping
    public BasicResponseDto<List<PostDto>> getPostByCategory(@RequestParam("category") String category) {
        return postService.findByCategory(category);
    }
}
