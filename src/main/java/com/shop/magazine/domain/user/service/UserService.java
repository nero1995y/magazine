package com.shop.magazine.domain.user.service;

import com.shop.magazine.domain.user.dto.UserResponseDto;
import com.shop.magazine.domain.user.dto.UserSaveRequestDto;
import com.shop.magazine.domain.user.dto.UserUpdateRequestDto;
import com.shop.magazine.domain.user.dto.UsersResponseDto;
import com.shop.magazine.domain.user.entity.User;
import com.shop.magazine.domain.user.exception.AlreadyUserException;
import com.shop.magazine.domain.user.exception.UserNotFoundException;
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

        verifyEmail(requestDto.getEmail());

        return userRepository
                .save(requestDto.toEntity())
                .getId();
    }

    private void verifyEmail(String email) {
        userRepository
                .findByEmail(email)
                .ifPresent((user) -> {
                    throw new AlreadyUserException();
                });
    }
    // TODO (nero) exception 테스트 만들기
    public UserResponseDto findById(Long id) {
        return new UserResponseDto(findUser(id));
    }

    public UsersResponseDto findAll() {
        return  UsersResponseDto.of(userRepository.findAll());
    }

    @Transactional
    public void update(Long id, UserUpdateRequestDto requestDto) {
        User user = findUser(id);
        user.update(id, requestDto.toEntity());
    }

    @Transactional
    public void remove(Long id) {
        User user = findUser(id);
        userRepository.deleteById(user.getId());
    }


    public User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

}


