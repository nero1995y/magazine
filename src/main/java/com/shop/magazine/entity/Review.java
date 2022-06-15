package com.shop.magazine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "REVIEW")
@NoArgsConstructor
@Getter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "review_title")
    private String title;

    @Column(name = "review_contents")
    private String contents;

    @Column(name = "review_star")
    private int star;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;

}
