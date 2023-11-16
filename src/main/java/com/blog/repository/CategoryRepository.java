package com.blog.repository;

import com.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Set<Category> findCategoriesByIdIn(Set<String> ids);
}
