package com.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto implements Serializable {
    private String id;

    private String title;

    private String summary;

    private String slug;

    private String content;

    private String status;

    private String avatarUrl;

    private String isDeleted;

    private Integer priority;

    private Long viewCounter;

    private Set<CategoryDto> categories = new HashSet<>();
}
