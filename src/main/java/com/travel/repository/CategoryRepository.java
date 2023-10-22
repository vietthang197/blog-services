package com.travel.repository;

import com.travel.entity.Category;
import com.travel.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Set<Category> findCategoriesByIdIn(Set<String> ids);
}
