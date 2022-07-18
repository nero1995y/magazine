package com.shop.magazine.domain.post.service;

import com.shop.magazine.domain.post.dto.PostSaveRequestDto;
import com.shop.magazine.domain.post.dto.PostUpdateRequestDto;
import com.shop.magazine.domain.post.dto.PostsResponseDto;
import com.shop.magazine.domain.post.entity.Post;
import com.shop.magazine.domain.post.exception.PostNotFoundException;
import com.shop.magazine.domain.post.repository.PostRepository;
import com.shop.magazine.domain.user.entity.User;
import com.shop.magazine.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Transactional
    public Long register(Long id, PostSaveRequestDto requestDto) {

        User user = userService.findUser(id);

        return postRepository
                .save(requestDto.toEntity(user))
                .getId();
    }
//
//    public PostResponse findPostSingle(Long id, Long userId) {
//
//        UserResponseDto findUser = userService.findById(userId);
//
//        return new PostResponse(findPost(id), findUser);
//    }

    public Post findPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
    }
    public PostsResponseDto findAll() {
        return PostsResponseDto.of(postRepository.findAll());
    }

    @Transactional
    public void remove(Long id) {
        Post post = findPost(id);
        postRepository.deleteById(post.getId());
    }

    @Transactional
    public void update(Long id, PostUpdateRequestDto requestDto) {
        Post post = findPost(id);
        post.update(id, requestDto.toEntity(post.getUser()));
    }
}