package com.shop.magazine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SELLER_POST")
@NoArgsConstructor
@Getter
public class SellerPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long id;

    @Column(name = "post_id")
    private Long postId;

}

