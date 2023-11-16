package com.blog.repository;

import com.blog.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    @EntityGraph(attributePaths = {"categories", "tags", "comments"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Post> findAllByCategoriesId(String categoryId);

    @EntityGraph(attributePaths = {"categories", "tags", "comments"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Post> findById(String id);
}
