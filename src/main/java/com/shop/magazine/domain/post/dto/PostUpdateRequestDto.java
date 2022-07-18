package com.shop.magazine.domain.post.dto;

import com.shop.magazine.domain.post.entity.Post;
import com.shop.magazine.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {
    private Long id;
    private String title;
    private String contents;
    private String status;

    @Builder
    public PostUpdateRequestDto(Long id, String title, String contents, String status) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.status = status;
    }

    public Post toEntity(User user) {
        return Post.builder()
                .title(title)
                .contents(contents)
                .status(status)
                .user(user)
                .build();
    }
}
