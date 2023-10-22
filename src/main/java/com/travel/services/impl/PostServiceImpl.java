package com.travel.services.impl;

import com.travel.constants.Constant;
import com.travel.dto.BasicResponseDto;
import com.travel.dto.PostDto;
import com.travel.entity.Category;
import com.travel.entity.Location;
import com.travel.entity.Post;
import com.travel.entity.Tag;
import com.travel.mapper.PostMapper;
import com.travel.repository.CategoryRepository;
import com.travel.repository.LocationRepository;
import com.travel.repository.PostRepository;
import com.travel.repository.TagRepository;
import com.travel.services.PostService;
import com.travel.services.vm.CreatePostVM;
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
    private LocationRepository locationRepository;

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

        Optional<Location> locationOptional = Optional.empty();

        if (Strings.isNotBlank(request.getLocationId())) {
            locationOptional = locationRepository.findById(request.getLocationId());
            if (locationOptional.isEmpty()) {
                return BasicResponseDto.<PostDto>builder().status(Constant.ERR_CODE.ERR_400).message("Location not found!").data(null).build();
            }
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
                .location(locationOptional.orElse(null))
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
}
