package com.shop.magazine.domain.user.service;

import com.shop.magazine.domain.user.dto.UserResponseDto;
import com.shop.magazine.domain.user.dto.UserSaveRequestDto;
import com.shop.magazine.domain.user.dto.UserUpdateRequestDto;
import com.shop.magazine.domain.user.dto.UsersResponseDto;
import com.shop.magazine.domain.user.entity.User;
import com.shop.magazine.domain.user.exception.AlreadyUserException;
import com.shop.magazine.domain.user.exception.UserNotFoundException;
import com.shop.magazine.domain.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("등록한다_유저")
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

    @DisplayName("검증한다_이메일_중복 ")
    @Test
    void verifyEmail() {
        // given
        userRepository.save(getUser());
        UserSaveRequestDto saveRequestDto = userSaveRequestDto();
        // when
        // then
        assertThatThrownBy(() -> userService.register(saveRequestDto))
                .isInstanceOf(AlreadyUserException.class);
        
    }

    @DisplayName("조회한다_단건_유저")
    @Test
    void findById() {
        // given
        User save = userRepository.save(getUser());

        // when
        UserResponseDto findUser = userService.findById(save.getId());

        // then
        assertThat(findUser.getEmail()).isEqualTo(save.getEmail());

    }

    @DisplayName("조회한다_여러건_유저")
    @Test
    void findAll() {
        // given
        userRepository.save(getUser());
        userRepository.save(getUser());

        // when
        UsersResponseDto users = userService.findAll();

        // then
        assertThat(users.getUserResponseList().size()).isEqualTo(2);
    }

    @DisplayName("업데이한다_유저")
    @Test
    void update() {
        // given
        User save = userRepository.save(getUser());
        UserResponseDto responseDto = userService.findById(save.getId());
        UserUpdateRequestDto user = UserUpdateRequestDto.
                builder()
                .username("업데이트")
                .build();
        // when
        userService.update(responseDto.getId(), user);


        // then
        Optional<User> user1 = userRepository.findById(save.getId());

        assertThat(user1.orElse(null).getUsername())
                .isEqualTo(user.getUsername());
    }

    @DisplayName("삭제한다_유저")
    @Test
    void remove() {
        // given
        User save = userRepository.save(getUser());

        // when
        userService.remove(save.getId());

        //then
        assertThatThrownBy(() -> userService.findById(save.getId()))
                .isInstanceOf(UserNotFoundException.class);
    }


    private User getUser() {
        return User.builder()
                .email("test@naver.com")
                .username("testId")
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