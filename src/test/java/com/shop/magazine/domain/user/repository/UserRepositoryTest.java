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
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @DisplayName("저장한다 유저를")
    @Test
    void save() {
        // given
        User user = getUser();

        // when
        User saveUser = userRepository.save(user);

        // then
        Optional<User> findUser = userRepository.findById(saveUser.getId());
        assertThat(saveUser.getName())
                .isEqualTo(findUser.orElseThrow(
                        ()-> new NoSuchElementException("user not found")).getName());
    }

    @DisplayName("불러온다 유저들을")
    @Test
    void findUsers() {
        // given
        User saveUser = userRepository.save(getUser());
        User saveUser2 = userRepository.save(getUser2());

        // when
        List<User> userList = userRepository.findAll();

        // then
        assertThat(userList.size()).isEqualTo(2);
    }

    @DisplayName("불러온다 유저 단건")
    @Test
    void findUser() {
        // given
        User saveUser = userRepository.save(getUser());

        // when
        Optional<User> findUser = userRepository.findById(saveUser.getId());

        // then
        assertThat(findUser.orElseThrow(
                ()-> new NoSuchElementException("user not found")))
                .isEqualTo(saveUser);
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