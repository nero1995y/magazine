package com.shop.magazine.domain.user.repository;

import com.shop.magazine.domain.user.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> search();
}
