package com.shop.magazine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "QNA")
@NoArgsConstructor
@Getter
public class Qna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "user_Id")
    private Long userId;

    @Column(name = "contents")
    private String contents;

}
