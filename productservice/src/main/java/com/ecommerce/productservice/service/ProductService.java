package com.ecommerce.productservice.service;

import com.ecommerce.productservice.model.Product;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductService {
    Mono<Product> saveProduct(Product product);
    Flux<Product> getAllProducts();
    Flux<Product> saveAllFromCSV(FilePart filePart);
}
