package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "post")
@Indexed
@ToString
public class Post extends SuperEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @GenericField(sortable = Sortable.YES)
    private String id;

    @Column(length = 1000)
    @FullTextField(analyzer = "customAnalyzer")
    @NotNull
    private String title;

    // Mô tả ngắn
    @Column(length = 1200)
    @NotNull
    private String summary;

    // đường dẫn ngắn tới bài viết
    @Column(length = 500, unique = true)
    @FullTextField(analyzer = "customAnalyzer")
    @NotNull
    private String slug;

    @Column(length = 200)
    @NotNull
    private String avatarId;

    // Nội dung bài viết
    @Lob
    @FullTextField(analyzer = "customAnalyzer")
    @NotNull
    private String content;

    @FullTextField(analyzer = "customAnalyzer")
    @NotNull
    private String status;

    @FullTextField
    @NotNull
    private String isDeleted;

    // độ ưu tiên bài viết
    @GenericField
    @NotNull
    private Integer priority;

    @GenericField
    @NotNull
    private Long viewCounter;

    // Menu chủ đề chính
    @IndexedEmbedded
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_category", joinColumns = {
            @JoinColumn(name = "fk_post_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "fk_category_id")
    })
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<Category> categories = new HashSet<>();

    // Tags gắn với bài viết
    @IndexedEmbedded
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_tag", joinColumns = {
            @JoinColumn(name = "fk_post_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "fk_tag_id")
    })
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "post")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();
}
