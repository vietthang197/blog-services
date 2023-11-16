package com.blog.services.vm;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdatePostVM implements Serializable {
    @NotBlank(message = "Id can not be null")
    private String id;
    @NotBlank(message = "title can not be null")
    private String title;
    @NotBlank(message = "summary can not be null")
    private String summary;
    @NotBlank(message = "slug can not be null")
    private String slug;
    @NotBlank(message = "content can not be null")
    private String content;
    private Integer priority;
    private Set<String> categories;
    private Set<String> tags;
    @NotBlank(message = "status can not be null")
    private String status;
}
