package com.shop.magazine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER_COMMENT")
@NoArgsConstructor
@Getter
public class UserComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_comment_id")
    private Long id;

    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "user_id")
    private Long UserId;


}
