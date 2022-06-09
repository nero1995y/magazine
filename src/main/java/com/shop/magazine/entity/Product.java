package com.shop.magazine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String size;

    @Column(name =  "seller_id")



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category categoryId;
}
