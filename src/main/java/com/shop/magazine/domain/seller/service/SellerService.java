package com.shop.magazine.domain.seller.service;

import com.shop.magazine.domain.seller.dto.SellerResponse;
import com.shop.magazine.domain.seller.dto.SellerSaveRequestDto;
import com.shop.magazine.domain.seller.dto.SellerUpdateRequestDto;
import com.shop.magazine.domain.seller.dto.SellersResponse;
import com.shop.magazine.domain.seller.entity.Seller;
import com.shop.magazine.domain.seller.exception.AlreadySellerException;
import com.shop.magazine.domain.seller.exception.SellerNotFoundException;
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
        verifyEmail((requestDto.getEmail()));
        return sellerRepository
                .save(requestDto.toEntity())
                .getId();
    }

    public SellerResponse findById(Long id) {
        return new SellerResponse(findSeller(id));
    }

    public SellersResponse findAll() {
        return SellersResponse.of(sellerRepository.findAll());
    }

    @Transactional
    public void update(Long id, SellerUpdateRequestDto requestDto){
        Seller seller = findSeller(id);
        seller.update(id, requestDto.toEntity());
    }


    private Seller findSeller(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(SellerNotFoundException::new);
    }


    private void verifyEmail(String email) {
        sellerRepository
                .findByEmail(email)
                .ifPresent((user) -> {
                    throw  new AlreadySellerException();
                });
    }
}
