package com.shop.magazine.domain.seller.entity;

import com.shop.magazine.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SELLER")
@NoArgsConstructor
@Getter
public class Seller {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "seller_id")
    private Long id;

    @Column(name =  "seller_name")
    private String name;

    @Column(name = "seller_email")
    private String email;


    @OneToMany(mappedBy = "sellerId", cascade = CascadeType.ALL)
    private List<Product> productList;


    @Builder
    public Seller(Long id, String name, String email, List<Product> productList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.productList = productList;
    }


    public static Seller createSeller(Seller seller, List<Product> productList) {
        return Seller.builder()
                .email(seller.getEmail())
                .name(seller.getName())
                .productList(productList)
                .build();
    }

    public void update(Long updateId, Seller seller) {
        this.id = updateId;
        this.name = seller.getName();
        this.email = seller.getEmail();
    }
}
