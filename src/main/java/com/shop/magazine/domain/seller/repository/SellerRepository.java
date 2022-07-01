package com.shop.magazine.domain.seller.repository;

import com.shop.magazine.domain.seller.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {

}
