package com.shop.magazine.domain.post.entity;


import com.shop.magazine.domain.user.entity.User;
import com.shop.magazine.global.entity.AuditingEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "POST")
@NoArgsConstructor
@Getter
public class Post extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "post_title", length = 10)
    private String title;


    @Column(name = "post_contents", columnDefinition = "TEXT")
    private String contents;

    @Column(name = "post_status")
    // TODO enum 타입으로 바꾸기
    private String status;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;



    @Builder
    public Post(Long id, String title, String contents, String status, User user) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.status = status;
        this.user = user;
        addUser(this);
    }

    public void update(Long id, Post post) {
        this.id = id;
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.status = post.getStatus(); ;
        this.user = post.getUser();
    }

    public void addUser(Post post) {
        user.add(post);
    }
}
