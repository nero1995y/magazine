package com.shop.magazine.domain.seller.dto;

import com.shop.magazine.domain.seller.entity.Seller;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SellerSaveRequestDto {
    private String name;
    private String email;


    @Builder
    public SellerSaveRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Seller toEntity() {
        return Seller.builder()
                .name(this.name)
                .email(this.email)
                .build();
    }
}
