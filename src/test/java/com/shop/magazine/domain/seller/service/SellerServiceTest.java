package com.shop.magazine.domain.seller.service;

import com.shop.magazine.domain.seller.dto.SellerResponse;
import com.shop.magazine.domain.seller.dto.SellerSaveRequestDto;
import com.shop.magazine.domain.seller.dto.SellersResponse;
import com.shop.magazine.domain.seller.entity.Seller;
import com.shop.magazine.domain.seller.exception.AlreadySellerException;
import com.shop.magazine.domain.seller.repository.SellerRepository;
import com.shop.magazine.domain.user.dto.UserSaveRequestDto;
import com.shop.magazine.domain.user.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
class SellerServiceTest {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private SellerRepository sellerRepository;

    @AfterEach
    public void cleanup() {
        sellerRepository.deleteAll();
    }

    @DisplayName("등록한다_판매자")
    @Test
    void register() {

        // given
        SellerSaveRequestDto testSeller = getSellerSaveRequestDto();

        // when
        Long register = sellerService.register(testSeller);

        // then
        Optional<Seller> findSeller = sellerRepository.findById(register);
        assertThat(register).isEqualTo(
                findSeller.orElseThrow(
                                () -> new NoSuchElementException("seller not found"))
                        .getId());

    }

    @DisplayName("검증한다_이메일_중복_예외 발생")
    @Test
    void verifyEmail() {
        // given
        sellerRepository.save(getSeller());

        // when then
        assertThatThrownBy(() ->  sellerService.register(getSellerSaveRequestDto()))
                .isInstanceOf(AlreadySellerException.class);
    }

    @DisplayName("조회한다_판매자_단건")
    @Test
    void findById() {
        // given
        Seller seller = sellerRepository.save(getSeller());

        // when
        SellerResponse findSeller = sellerService.findById(seller.getId());

        // then
        assertThat(findSeller.getEmail()).isEqualTo(seller.getEmail());
    }

    @DisplayName("조회한다_판매자_여러건")
    @Test
    void findAll() {
        // given

        for (int i = 0; i < 100; i++) {
           sellerRepository.save(Seller.builder()
                   .email("test"+ i + "@naver.com")
                   .name("testSellerName")
                   .build());
        }

        // when
        SellersResponse findList = sellerService.findAll();

        int size = findList.getSellerResponseList().size();

        assertThat(size).isEqualTo(100);
    }

    @DisplayName("업데이트한다_판매자")
    @Test
    void update



    private Seller getSeller() {
        return Seller.builder()
                .email("test@naver.com")
                .name("testSellerName")
                .build();
    }


    private SellerSaveRequestDto getSellerSaveRequestDto() {
        return SellerSaveRequestDto.builder()
                .email("test@naver.com")
                .name("testSeller")
                .build();
    }
}