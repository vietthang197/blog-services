package com.blog.controller;

import com.blog.dto.BasicResponseDto;
import com.blog.dto.PostDto;
import com.blog.services.PostService;
import com.blog.services.vm.CreatePostVM;
import com.blog.services.vm.UpdatePostVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping
    public BasicResponseDto<PostDto> updatePost(@RequestBody UpdatePostVM updatePostVM) {
        return postService.update(updatePostVM);
    }

    @GetMapping
    public BasicResponseDto<List<PostDto>> getPostByCategory(@RequestParam("category") String category) {
        return postService.findByCategory(category);
    }

    @DeleteMapping
    public BasicResponseDto<Void> deletePost(@RequestBody UpdatePostVM updatePostVM) {
        return postService.deletePost(updatePostVM);
    }
}
