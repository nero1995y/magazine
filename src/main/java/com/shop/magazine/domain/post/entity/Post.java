package com.shop.magazine.domain.post.entity;

import com.shop.magazine.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "POST")
@NoArgsConstructor
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "post_title", length = 10)
    private String title;


    @Column(name = "post_contents", columnDefinition = "TEXT")
    private String contents;

    @Column(name = "post_status")
    // TODO enum 타입으로 바꾸기
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product productId;

    @Builder
    public Post(Long id, LocalDateTime createdDate, LocalDateTime modifiedDate, String title, String contents, String status, Product productId) {
        this.id = id;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.title = title;
        this.contents = contents;
        this.status = status;
        this.productId = productId;
    }
}
