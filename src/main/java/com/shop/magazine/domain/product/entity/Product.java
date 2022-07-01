package com.shop.magazine.domain.product.entity;

import com.shop.magazine.domain.post.entity.Post;
import com.shop.magazine.domain.seller.entity.Seller;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "product_id")
    private Long id;

    @Column(name =  "product_name")
    private String name;

    @Column(name =  "product_link")
    private String link;

    @Column(name =  "product_size")
    private int size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller sellerId;


    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL)
    private List<Post> postList;

    @Builder
    public Product(Long id, String name, String link, int size, Seller sellerId, List<Post> postList) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.size = size;
        this.sellerId = sellerId;
        this.postList = postList;
    }
}
