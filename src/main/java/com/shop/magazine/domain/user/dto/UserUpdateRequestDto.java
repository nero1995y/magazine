package com.shop.magazine.domain.user.dto;

import com.shop.magazine.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String email;
    private String name;
    private String password;
    private String phone;

    @Builder
    public UserUpdateRequestDto(
                                String email,
                                String name,
                                String password,
                                String phone) {

        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }
    public User toEntity() {
        return User.builder()
                .email(this.email)
                .name(this.name)
                .password(this.password)
                .phone(this.phone)
                .build();

    }
}
