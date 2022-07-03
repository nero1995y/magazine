package com.shop.magazine.domain.seller.dto;

import com.shop.magazine.domain.seller.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SellersResponse {

    private List<SellerResponse> sellerResponseList;

    public static SellersResponse of (List<Seller> sellers) {
        return new SellersResponse(toResponse(sellers));
    }

    private static List<SellerResponse> toResponse(List<Seller> sellers){
        return sellers.stream()
                .map(SellerResponse::new)
                .collect(Collectors.toList());
    }
}
