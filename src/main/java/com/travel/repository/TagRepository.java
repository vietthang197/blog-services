package com.travel.repository;

import com.travel.entity.Post;
import com.travel.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    Set<Tag>findAllByIdIn(Set<String> ids);
}
