package com.shop.magazine.domain.seller.dto;

import com.shop.magazine.domain.seller.entity.Seller;
import lombok.Getter;

@Getter
public class SellerResponse {

    private Long id;
    private String email;
    private String name;

    public SellerResponse(Seller entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
    }
}
