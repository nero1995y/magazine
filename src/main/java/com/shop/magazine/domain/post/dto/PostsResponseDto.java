package com.shop.magazine.domain.post.dto;

import com.shop.magazine.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostsResponseDto {

    private List<PostResponseDto> postResponses;

    public static PostsResponseDto of (List<Post> posts) {
        return new PostsResponseDto(toResponse(posts));
    }

    public static List<PostResponseDto> toResponse(List<Post> posts) {
       return posts.stream()
               .map(PostResponseDto::new)
               .collect(Collectors.toList());
    }
}
