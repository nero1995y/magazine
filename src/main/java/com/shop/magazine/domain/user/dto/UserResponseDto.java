package com.shop.magazine.domain.user.dto;

import com.shop.magazine.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long id;
    private String email;
    private String name;
    private String password;
    private String phone;

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.password = entity.getPassword();
        this.phone = entity.getPhone();
    }
}