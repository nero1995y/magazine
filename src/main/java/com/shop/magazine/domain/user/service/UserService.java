package com.shop.magazine.domain.user.service;

import com.shop.magazine.domain.user.dto.UserSaveRequestDto;
import com.shop.magazine.domain.user.entity.User;
import com.shop.magazine.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long register(UserSaveRequestDto requestDto) {

        return userRepository
                .save(requestDto.toEntity())
                .getId();
    }
}


