package com.shop.magazine.domain.post.api;

import com.shop.magazine.domain.post.dto.PostResponseDto;
import com.shop.magazine.domain.post.dto.PostSaveRequestDto;
import com.shop.magazine.domain.post.dto.PostUpdateRequestDto;
import com.shop.magazine.domain.post.dto.PostsResponseDto;
import com.shop.magazine.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;

    @PostMapping("api/v1/post")
    public ResponseEntity<Long> register(@RequestBody @Valid PostSaveRequestDto requestDto) {

        return ResponseEntity.ok(postService.register(requestDto));
    }

    @GetMapping("api/v1/posts")
    public ResponseEntity<PostsResponseDto> findList() {

        return ResponseEntity.ok(postService.findAll());

    }

    @GetMapping("api/v1/post/{id}")
    public ResponseEntity<PostResponseDto> findById(@PathVariable Long id) {

        return ResponseEntity.ok(postService.findPostSingle(id));
    }


    @PatchMapping("api/v1/post/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody @Valid PostUpdateRequestDto requestDto) {

        postService.update(id, requestDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("api/v1/post/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {

        postService.remove(id);

        return ResponseEntity.ok().build();
    }
}
