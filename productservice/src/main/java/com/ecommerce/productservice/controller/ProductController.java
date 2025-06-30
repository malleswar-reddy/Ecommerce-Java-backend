package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Mono<Product> create(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping
    public Flux<Product> getAll() {
        return productService.getAllProducts();
    }

    @PostMapping("/upload")
    public Flux<Product> uploadCSV(@RequestPart("file") FilePart filePart) {
        return productService.saveAllFromCSV(filePart);
    }
}
