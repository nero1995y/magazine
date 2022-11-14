package com.shop.magazine.domain.user.entity;

import com.shop.magazine.domain.post.entity.Post;
import com.shop.magazine.global.entity.AuditingEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@NoArgsConstructor
@Getter
public class User extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_email")
    @NotNull
    private String email;

    @Column(name = "user_name")
    @NotNull
    private String username;

    @Column(name = "user_password")
    @NotNull
    private String password;

    @Column(name = "user_phone")
    @NotNull
    private String phone;

    @Column(name = "user_role")
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();

    @Builder
    public User(Long id, String email, String username, String password, String phone) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    /*  업데이트 */
    public void update(Long id,User user) {
        this.id = id;
        this.email = user.email;
        this.username = user.username;
        this.password = user.password;
        this.phone = user.phone;
    }

    public void add(Post post) {
        postList.add(post);
    }

}
