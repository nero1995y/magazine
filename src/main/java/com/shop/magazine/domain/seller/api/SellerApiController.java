package com.shop.magazine.domain.seller.api;

import com.shop.magazine.domain.seller.dto.SellerResponse;
import com.shop.magazine.domain.seller.dto.SellerSaveRequestDto;
import com.shop.magazine.domain.seller.dto.SellerUpdateRequestDto;
import com.shop.magazine.domain.seller.dto.SellersResponse;
import com.shop.magazine.domain.seller.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SellerApiController {

    private final SellerService sellerService;

    @PostMapping("api/v1/seller")
    public ResponseEntity<Long> register(@RequestBody @Valid SellerSaveRequestDto requestDto) {
        return ResponseEntity.ok(sellerService.register(requestDto));
    }

    @GetMapping("api/v1/sellers")
    public ResponseEntity<SellersResponse> findList() {
        return ResponseEntity.ok(sellerService.findAll());
    }

    @GetMapping("api/v1/seller/{id}")
    public ResponseEntity<SellerResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(sellerService.findById(id));
    }

    @PatchMapping("api/v1/seller/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody @Valid SellerUpdateRequestDto requestDto) {
        sellerService.update(id, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("api/v1/seller/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        sellerService.remove(id);
        return ResponseEntity.ok().build();
    }
}
