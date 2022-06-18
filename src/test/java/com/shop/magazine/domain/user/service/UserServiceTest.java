package com.shop.magazine.domain.user.service;

import com.shop.magazine.domain.user.dto.UserSaveRequestDto;
import com.shop.magazine.domain.user.entity.User;
import com.shop.magazine.domain.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @DisplayName("등록_유저")
    @Test
    void register() {

        // given
        UserSaveRequestDto saveRequestDto = userSaveRequestDto();

        // when
        Long register = userService.register(saveRequestDto);

        // then
        Optional<User> findUser = userRepository.findById(register);
        assertThat(register).isEqualTo(
                findUser.orElseThrow(
                        () ->new NoSuchElementException("user not found"))
                        .getId());

    }

    private User getUser() {
        return User.builder()
                .email("test@naver.com")
                .name("testId")
                .password("1234")
                .phone("01022223333")
                .build();
    }

    private UserSaveRequestDto userSaveRequestDto() {
        return UserSaveRequestDto.builder()
                .email("test@naver.com")
                .name("testId")
                .password("1234")
                .phone("01022223333")
                .build();
    }

}