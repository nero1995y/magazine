package com.shop.magazine.domain.user.repository;

import com.shop.magazine.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryCustomTest {
    @Autowired
    UserRepository userRepository;

    @DisplayName("불러온다 유저 이메일 단건")
    @Test
    void findUser() {
        // given
        User saveUser = userRepository.save(getUser());

        // when
        Optional<User> findUser = userRepository.findByEmail(saveUser.getEmail());

        // then
        assertThat(findUser.orElseThrow(
                () -> new NoSuchElementException("user not found"))
                .getEmail())
                .isEqualTo("test@naver.com");
    }


    private User getUser() {
        return User.builder()
                .email("test@naver.com")
                .name("testId")
                .password("1234")
                .phone("01022223333")
                .build();
    }

    private User getUser2() {
        return User.builder()
                .email("test2@naver.com")
                .name("tes2tId")
                .password("1234")
                .phone("01022223333")
                .build();
    }



}