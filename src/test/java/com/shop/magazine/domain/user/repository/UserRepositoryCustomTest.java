package com.shop.magazine.domain.user.repository;

import com.shop.magazine.domain.user.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryCustomTest {
    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        userRepository.deleteAll();
    }

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

    @DisplayName("조회한다_유저_리스트_이름을")
    @Test
    void findUsernames() {
        // given
        User save = userRepository.save(getUser());
        User save2 = userRepository.save(getUser2());

        String username = getUser().getUsername();
        String username1 = getUser2().getUsername();

        List<String> usernameList = List.of(username, username1);

        //when
        List<User> findUsernameList = userRepository.findByUsernames(usernameList);

        assertThat(findUsernameList).contains(save, save2);
        assertThat(findUsernameList).containsExactlyInAnyOrder(save, save2);
    }

    @DisplayName("조회한다_유저_리스트")
    @Test
    void findAll() {

        // given
        for (int i = 0; i < 100; i++) {
            userRepository.save(User.builder()
                    .email("test@naver.com")
                    .username("testId" + i)
                    .password("1234")
                    .phone("01022223333")
                    .build());
        }

        // when
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<User> userList = userRepository.findAllOrderByUsernameAsc(pageRequest);

        // then
        assertThat(userList
                .getContent()
                .size())
                .isEqualTo(10);
        // TODO 검증 추가
        //assertThat(userList.getContent()).containsExactlyInAnyOrder()
        //containsExactlyInAnyOrderElementsOf
        // 현재 페이지 번호
        assertThat(userList
                .getNumber()).isEqualTo(0);

        assertThat(userList
                .isFirst()).isTrue();

        assertThat(userList
                .hasNext()).isTrue();
    }

    @DisplayName("커스텀_유저_검색")
    @Test
    void search() {

        // when
        List<User> search = userRepository.search();

        search.stream()
                .forEach(user -> System.out.println(user.getUsername()));

    }

    private User getUser() {
        return User.builder()
                .email("test@naver.com")
                .username("testId")
                .password("1234")
                .phone("01022223333")
                .build();
    }

    private User getUser2() {
        return User.builder()
                .email("test2@naver.com")
                .username("tes2tId")
                .password("1234")
                .phone("01022223333")
                .build();
    }


}