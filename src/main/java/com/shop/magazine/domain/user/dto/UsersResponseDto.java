package com.shop.magazine.domain.user.dto;

import com.shop.magazine.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponseDto {
    private List<UserResponseDto> userResponseList;

    public static UsersResponseDto of (List<User> users) {
        return new UsersResponseDto(toResponse(users));
    }

    private static List<UserResponseDto> toResponse(List<User> users) {
        return users.stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }
}
