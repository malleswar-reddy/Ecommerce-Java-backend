package com.ecommerce.sellerservice.service;

import com.ecommerce.sellerservice.dtos.SellerRequest;
import com.ecommerce.sellerservice.dtos.SellerResponse;
import com.ecommerce.sellerservice.model.Seller;
import com.ecommerce.sellerservice.repository.SellerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepo sellerRepository;

    @Override
    public Mono<SellerResponse> registerSeller(SellerRequest request) {
        Seller seller = Seller.builder()
                //id((long) (Math.random() * Long.MAX_VALUE)) // Dynamically generated random ID

                .name(request.getName())
                .email(request.getEmail())
                .contactNumber(request.getContactNumber())
                .verified(request.isVerified())
                .build();


        return sellerRepository.save(seller)
                .map(this::mapToResponse);
    }

    @Override
    public Mono<SellerResponse> verifySeller(Long sellerId) {
        return sellerRepository.findById(sellerId)
                .switchIfEmpty(Mono.error(new RuntimeException("Seller not found")))
                .flatMap(seller -> {
                    seller.setVerified(true);
                    return sellerRepository.save(seller);
                })
                .map(this::mapToResponse);
    }

    @Override
    public Mono<SellerResponse> getSellerById(Long id) {
        return sellerRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Seller not found")))
                .map(this::mapToResponse);
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
