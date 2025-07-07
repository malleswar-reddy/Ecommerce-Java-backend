package com.ecommerce.sellerservice.repository;

import com.ecommerce.sellerservice.model.Seller;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.Optional;

public interface SellerRepo extends R2dbcRepository<Seller, Long>{

  Optional<Seller> findByEmail(String email);

}
