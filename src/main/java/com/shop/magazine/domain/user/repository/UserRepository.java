package com.shop.magazine.domain.user.repository;

import com.shop.magazine.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
