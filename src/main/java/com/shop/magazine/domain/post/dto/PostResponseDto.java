package com.shop.magazine.domain.post.dto;

import com.shop.magazine.domain.post.entity.Post;
import com.shop.magazine.domain.user.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {

    private Long id;
    private String title;
    private String contents;
    private String status;

    private UserResponseDto user;

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.status = entity.getStatus();
    }
}
