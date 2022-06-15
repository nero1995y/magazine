package com.shop.magazine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SELLER_COMMENT")
@NoArgsConstructor
@Getter
public class SellerComment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_comment_id")
    private Long id;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "comment_id")
    private Long commentId;
}

