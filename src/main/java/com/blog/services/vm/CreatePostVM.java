package com.blog.services.vm;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreatePostVM implements Serializable {

    private String title;
    private String summary;
    private String slug;
    private String content;
    private Integer priority;
    private Set<String> categories;
    private Set<String> tags;
}
