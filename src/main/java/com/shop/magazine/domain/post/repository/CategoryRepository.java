package com.shop.magazine.domain.post.repository;

import com.shop.magazine.domain.post.entity.Category;
import com.shop.magazine.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
