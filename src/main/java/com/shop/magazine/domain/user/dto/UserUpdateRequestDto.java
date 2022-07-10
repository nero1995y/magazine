package com.shop.magazine.domain.user.dto;

import com.shop.magazine.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String email;
    private String username;
    private String password;
    private String phone;

    @Builder
    public UserUpdateRequestDto(
                                String email,
                                String username,
                                String password,
                                String phone) {

        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }
    public User toEntity() {
        return User.builder()
                .email(this.email)
                .username(this.username)
                .password(this.password)
                .phone(this.phone)
                .build();

    }
}
