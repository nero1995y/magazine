package com.shop.magazine.domain.post.repository;

import com.shop.magazine.domain.post.entity.Post;
import com.shop.magazine.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    private Post getPost() {
        return Post.builder()
                .title("테스트 타이틀")
                .contents("테스트 컨텐츠")
                .status("타입1")
                .user(getUser())
                .build();
    }
    private Post getPost2() {
        return Post.builder()
                .title("테스트 타이틀2")
                .contents("테스트 컨텐츠2")
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

    @DisplayName("저장한다 포스트")
    @Test
    @Rollback(value = false)
    void save()  {
        // given
        Post post = getPost();

        // when
        Post savePost = postRepository.save(post);
        // then
        Post findPost = postRepository.findById(savePost.getId())
                .orElseThrow(() -> new NoSuchElementException("post not found"));

        assertThat(findPost).isEqualTo(savePost);
        assertThat(findPost.getContents()).isEqualTo(findPost.getContents());
    }

    @DisplayName("불러온다_포스트")
    @Test
    void findSeller() {
        // given
        Post savePost1 = postRepository.save(getPost());
        Post savePost2 = postRepository.save(getPost2());

        // when
        List<Post> postList = postRepository.findAll();

        // then
        assertThat(postList).contains(savePost1,savePost2);
        // assertThat(foundAbility).isNotPresent();
    }

    @DisplayName("삭제한다_판매자")
    @Test
    void deleteSeller() {
        // given
        Post savePost = postRepository.save(getPost());
        Optional<Post> findPost = postRepository.findById(savePost.getId());

        // when
        postRepository.delete(findPost
                .orElseThrow(() -> new NoSuchElementException("post not found")));

        // then
        Optional<Post> post = postRepository.findById(savePost.getId());

        assertThat(post.isPresent()).isEqualTo(false);
        assertThat(post).isEmpty();
        assertThat(post).isNotPresent();
    }
}