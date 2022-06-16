package com.shop.magazine.entity;

import com.shop.magazine.domain.post.entity.Post;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category categoryId;

    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL)
    private List<Post> postList;


}
