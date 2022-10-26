package com.shop.magazine.domain.post.dto;

import com.shop.magazine.domain.post.entity.Category;
import com.shop.magazine.domain.post.entity.Post;
import com.shop.magazine.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {

    private String title;
    private String contents;
    private String status;

    private Long userId;

    private Category category;

    @Builder
    public PostSaveRequestDto(String title, String contents, String status, Long userId, Category category) {
        this.title = title;
        this.contents = contents;
        this.status = status;
        this.userId = userId;
        this.category = category;
    }

    public Post toEntity(User user) {
        return Post.builder()
                .title(title)
                .contents(contents)
                .status(status)
                .user(user)
                .category(category)
                .build();
    }
}
