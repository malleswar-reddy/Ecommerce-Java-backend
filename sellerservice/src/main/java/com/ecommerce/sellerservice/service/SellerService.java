package com.ecommerce.sellerservice.service;



import com.ecommerce.sellerservice.dtos.SellerRequest;
import com.ecommerce.sellerservice.dtos.SellerResponse;
import com.ecommerce.sellerservice.dtos.SellerRequest;
import reactor.core.publisher.Mono;

public interface SellerService {
    Mono<SellerResponse> registerSeller(com.ecommerce.sellerservice.dtos.SellerRequest request);
    Mono<SellerResponse>  verifySeller(Long sellerId);
    Mono<SellerResponse>  getSellerById(Long id);
}

