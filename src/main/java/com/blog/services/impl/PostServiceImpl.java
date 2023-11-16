package com.blog.services.impl;

import com.blog.constants.Constant;
import com.blog.dto.BasicResponseDto;
import com.blog.dto.PostDto;
import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.Tag;
import com.blog.mapper.PostMapper;
import com.blog.repository.CategoryRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.TagRepository;
import com.blog.services.PostService;
import com.blog.services.vm.CreatePostVM;
import com.blog.services.vm.UpdatePostVM;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostMapper mapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public BasicResponseDto<PostDto> create(CreatePostVM request) {
        if (CollectionUtils.isEmpty(request.getCategories())) {
            return BasicResponseDto.<PostDto>builder().status(Constant.ERR_CODE.ERR_400).message("Category not found!").data(null).build();
        }

        if (CollectionUtils.isEmpty(request.getTags())) {
            return BasicResponseDto.<PostDto>builder().status(Constant.ERR_CODE.ERR_400).message("Tag not found!").data(null).build();
        }


        Set<Category> categoryList = categoryRepository.findCategoriesByIdIn(request.getCategories());
        Set<Tag> tagList = tagRepository.findAllByIdIn(request.getTags());

        Post post = Post.builder()
                .title(request.getTitle())
                .summary(request.getSummary())
                .slug(request.getSlug())
                .categories(categoryList)
                .tags(tagList)
                .priority(request.getPriority())
                .content(request.getContent())
                .isDeleted(Constant.STR_N)
                .status(Constant.ACTIVE)
                .build();
        postRepository.save(post);

        return BasicResponseDto.ok(mapper.toDto(post));
    }

    @Override
    @Transactional
    public BasicResponseDto<List<PostDto>> findByCategory(String category) {
        List<Post> posts = postRepository.findAllByCategoriesId(category);
        return BasicResponseDto.ok(mapper.toListDto(posts));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public BasicResponseDto<PostDto> update(UpdatePostVM request) {
        Optional<Post> postOptional = postRepository.findById(request.getId());
        if (postOptional.isEmpty()) {
            return BasicResponseDto.<PostDto>builder().status(Constant.ERR_CODE.ERR_400).message("Post not found!").data(null).build();
        }

        if (CollectionUtils.isEmpty(request.getCategories())) {
            return BasicResponseDto.<PostDto>builder().status(Constant.ERR_CODE.ERR_400).message("Category not found!").data(null).build();
        }

        if (CollectionUtils.isEmpty(request.getTags())) {
            return BasicResponseDto.<PostDto>builder().status(Constant.ERR_CODE.ERR_400).message("Tag not found!").data(null).build();
        }

        Set<Category> categoryList = categoryRepository.findCategoriesByIdIn(request.getCategories());
        Set<Tag> tagList = tagRepository.findAllByIdIn(request.getTags());

        Post post = postOptional.get();
        post.setTitle(request.getTitle());
        post.setSummary(request.getSummary());
        post.setSlug(request.getSlug());
        post.setCategories(categoryList);
        post.setTags(tagList);
        post.setPriority(request.getPriority());
        post.setContent(request.getContent());
        post.setStatus(request.getStatus());

        postRepository.save(post);
        return BasicResponseDto.ok(mapper.toDto(post));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public BasicResponseDto<Void> deletePost(UpdatePostVM request) {
        Optional<Post> postOptional = postRepository.findById(request.getId());
        if (postOptional.isEmpty()) {
            return BasicResponseDto.<Void>builder().status(Constant.ERR_CODE.ERR_400).message("Post not found!").data(null).build();
        }
        postRepository.delete(postOptional.get());
        return BasicResponseDto.ok();
    }
}
