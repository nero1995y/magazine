package com.shop.magazine.domain.post.repository;

import com.shop.magazine.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
