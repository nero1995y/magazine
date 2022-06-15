package com.shop.magazine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "COMMENT")
@NoArgsConstructor
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "comment_contents")
    private String contents;

    @Column(name = "update_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_date")
    private LocalDateTime createdDate;


    @Enumerated(EnumType.STRING)
    @Column(name = "comment_status")
    private CommentStatus commentStatus;
}
