package com.ecommerce.sellerservice.controller;





import com.ecommerce.sellerservice.dtos.SellerRequest;
import com.ecommerce.sellerservice.dtos.SellerResponse;
import com.ecommerce.sellerservice.model.Seller;
import com.ecommerce.sellerservice.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @PostMapping("/register")
    public  Mono<SellerResponse> register(@RequestBody SellerRequest request) {
        return sellerService.registerSeller(request);
    }

    @PutMapping("/{id}/verify")
    public  Mono<SellerResponse> verify(@PathVariable Long id) {
        return sellerService.verifySeller(id);
    }

    @GetMapping("/{id}")
    public Mono<SellerResponse> getSeller(@PathVariable Long id) {
        return sellerService.getSellerById(id);
    }
    private SellerResponse mapToResponse(Seller seller) {
        return SellerResponse.builder()
                .id(seller.getId())
                .name(seller.getName())
                .email(seller.getEmail())
                .contactNumber(seller.getContactNumber())
                .verified(seller.isVerified())
                .build();
    }
}


