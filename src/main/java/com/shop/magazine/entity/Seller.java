package com.shop.magazine.entity;

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
    private List<Product> postList;

}
