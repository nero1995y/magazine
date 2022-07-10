package com.shop.magazine.domain.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USER")
@NoArgsConstructor
@Getter
public class User {

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
}
