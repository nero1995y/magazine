package com.shop.magazine.domain.post.service;

import com.shop.magazine.domain.post.dto.PostResponseDto;
import com.shop.magazine.domain.post.dto.PostSaveRequestDto;
import com.shop.magazine.domain.post.dto.PostUpdateRequestDto;
import com.shop.magazine.domain.post.dto.PostsResponseDto;
import com.shop.magazine.domain.post.entity.Category;
import com.shop.magazine.domain.post.entity.Post;
import com.shop.magazine.domain.post.exception.PostNotFoundException;
import com.shop.magazine.domain.post.repository.CategoryRepository;
import com.shop.magazine.domain.post.repository.PostRepository;
import com.shop.magazine.domain.user.dto.UserSaveRequestDto;
import com.shop.magazine.domain.user.entity.User;
import com.shop.magazine.domain.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryRepository categoryRepository;

//    @AfterEach
//    public void cleanup() {
//        postRepository.deleteAll();
//    }


    private PostSaveRequestDto getPostSaveRequestDto(Long userId) {
        return PostSaveRequestDto.builder()
                .title("test 타이틀")
                .contents("test contents")
                .status("test type")
                .userId(userId)
                .category(getCategory())
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

    private Post getPost() {
        return Post.builder()
                .title("테스트 타이틀")
                .contents("테스트 컨텐츠")
                .status("타입1")
                .user(getUser())
                .build();
    }

    private User getUser() {
        return User.builder()
                .email("test2@naver.com")
                .username("tes1tId")
                .password("1234")
                .phone("01022223333")
                .build();
    }

    private Category getCategory() {
        return Category.builder()
                .name("JAVA")
                .build();
    }

    @DisplayName("등록한다_포스트")
    @Test

    //삭제할것
    @Rollback(value = false)
    void register() {

        // given
        Long userId = userService.register(userSaveRequestDto());
        PostSaveRequestDto requestDto = getPostSaveRequestDto(userId);

        // 카테고리
        categoryRepository.save(getCategory());

        // when
        Long register = postService.register(requestDto);

        // then
        Post findUser = postRepository.findById(register)
                .orElseThrow(() -> new NoSuchElementException("user not found"));

        assertThat(register).isEqualTo(findUser.getId());

    }

    @DisplayName("조회한다_포스트_단건")
    @Test
    void findById() {
        // given
        Long userId = userService.register(userSaveRequestDto());
        Long postSaveId = postService.register(getPostSaveRequestDto(userId));

        // when
        Post post = postService.findPost(postSaveId);

        // then
        assertThat(post.getId()).isEqualTo(postSaveId);
    }

    @DisplayName("조회한다_포스트_한건_DTO")
    @Test
    void findPostSingle() {
        // given
        Long userId = userService.register(userSaveRequestDto());
        Long postSaveId = postService.register(getPostSaveRequestDto(userId));

        //when
        PostResponseDto postSingle = postService.findPostSingle(postSaveId);

        assertThat(postSaveId).isEqualTo(postSingle.getId());
    }

    @DisplayName("조회한다_포스트_여러건")
    @Test
    void findAll() {
        // given

        Long userId = userService.register(userSaveRequestDto());
        PostSaveRequestDto requestDto = getPostSaveRequestDto(userId);
        PostSaveRequestDto requestDto2 = getPostSaveRequestDto(userId);

        Long register1 = postService.register(requestDto);
        Long register2 = postService.register(requestDto2);

        // when
        PostsResponseDto posts = postService.findAll();

        // then
        Post findPost1 = postService.findPost(register1);
        Post findPost2 = postService.findPost(register2);

        List<String> collect = posts
                .getPostResponses()
                .stream()
                .map(PostResponseDto::getTitle)
                .collect(toList());

        assertThat(collect)
                .contains(findPost1.getTitle(), findPost2.getTitle());
    }

    @DisplayName("업데이트한다_포스트")
    @Test
    @Rollback(value = false)
    void update() {
        // given
        Post save = postRepository.save(getPost());
        Post post = postService.findPost(save.getId());

        PostUpdateRequestDto update = PostUpdateRequestDto.builder()
                .contents("업데이트test")
                .build();
        // when
        postService.update(post.getId(), update);

        // then
        Post findPost = postService.findPost(save.getId());

        assertThat(findPost.getContents()).isEqualTo(findPost.getContents());

    }

    @DisplayName("삭제한다_포스트")
    @Test
    void remove() {
        // given
        Post save = postRepository.save(getPost());

        // when
        postService.remove(save.getId());

        // then
        assertThatThrownBy(() -> postService.findPost(save.getId()))
                .isInstanceOf(PostNotFoundException.class);
    }
}