package com.shop.magazine.domain.seller.dto;

import com.shop.magazine.domain.seller.entity.Seller;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SellerUpdateRequestDto {
    private String email;
    private String name;

    @Builder
    public SellerUpdateRequestDto(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public Seller toEntity() {
        return Seller.builder()
                .email(this.email)
                .name(this.name)
                .build();
    }
}
