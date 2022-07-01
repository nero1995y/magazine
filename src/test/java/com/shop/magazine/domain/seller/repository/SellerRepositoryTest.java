package com.shop.magazine.domain.seller.repository;

import com.shop.magazine.domain.product.entity.Product;
import com.shop.magazine.domain.seller.entity.Seller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SellerRepositoryTest {

    @Autowired
    SellerRepository sellerRepository;

    @DisplayName("저장한다 판매자를")
    @Test
    void save()  {
        // given
        Seller seller = getSeller();

        // when
        Seller save = sellerRepository.save(seller);

        // then
        Optional<Seller> findSeller = sellerRepository.findById(save.getId());

        Seller productName = findSeller.orElseThrow(
                () -> new NoSuchElementException("product not found"));


        assertThat(productName.getName()).isEqualTo(save.getName());
        assertThat(productName.getEmail()).isEqualTo(save.getEmail());
    }

    @DisplayName("불러온다_판매자_여러건")
    @Test
    void findSeller() {
        // given
        Seller saveSeller = sellerRepository.save(getSeller());
        Seller saveSeller2 = sellerRepository.save(getSeller2());

        // when
        List<Seller> sellerList = sellerRepository.findAll();

        // then
        assertThat(sellerList.size()).isEqualTo(2);
        assertThat(sellerList).containsExactly(saveSeller,saveSeller2);
    }

    @DisplayName("삭제한다_판매자")
    @Test
    void deleteSeller() {
        // given
        Seller seller = sellerRepository.save(getSeller());
        Optional<Seller> findSeller = sellerRepository.findById(seller.getId());
        // when
        sellerRepository.delete(findSeller.orElseThrow(
                () -> new NoSuchElementException("product not found")
        ));

        // then
        Optional<Seller> seller2 = sellerRepository.findById(findSeller.get().getId());
        assertThat(seller2.isPresent()).isEqualTo(false);
    }



    private Seller getSeller() {

        List<Product> productList = new ArrayList<>();

        return Seller.builder()
                .email("test@naver.com")
                .name("테스트이름")
                .productList(productList)
                .build();
    }

    private Seller getSeller2() {

        List<Product> productList = new ArrayList<>();

        return Seller.builder()
                .email("test@naver.com")
                .name("테스트이름2")
                .productList(productList)
                .build();
    }
}