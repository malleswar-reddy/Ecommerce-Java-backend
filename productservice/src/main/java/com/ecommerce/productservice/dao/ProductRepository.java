package com.ecommerce.productservice.dao;

import com.ecommerce.productservice.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
}
