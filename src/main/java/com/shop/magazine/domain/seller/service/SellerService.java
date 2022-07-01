package com.shop.magazine.domain.seller.service;

import com.shop.magazine.domain.seller.dto.SellerSaveRequestDto;
import com.shop.magazine.domain.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SellerService {

    private  final SellerRepository sellerRepository;

    @Transactional
    public Long register(SellerSaveRequestDto requestDto) {
        return sellerRepository
                .save(requestDto.toEntity())
                .getId();
    }
}
