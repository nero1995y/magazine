package com.shop.magazine.domain.post.service;

import com.shop.magazine.domain.post.dto.PostResponseDto;
import com.shop.magazine.domain.post.dto.PostSaveRequestDto;
import com.shop.magazine.domain.post.dto.PostUpdateRequestDto;
import com.shop.magazine.domain.post.dto.PostsResponseDto;
import com.shop.magazine.domain.post.entity.Post;
import com.shop.magazine.domain.post.exception.PostNotFoundException;
import com.shop.magazine.domain.post.repository.PostRepository;
import com.shop.magazine.domain.user.dto.UserResponseDto;
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
    public Long register(PostSaveRequestDto requestDto) {

        User user = userService.findUser(requestDto.getUserId());

        return postRepository
                .save(requestDto.toEntity(user))
                .getId();
    }

    public PostResponseDto findPostSingle(Long id) {

//        UserResponseDto user = userService.findById(userId);

        return new PostResponseDto(findPost(id));
    }

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