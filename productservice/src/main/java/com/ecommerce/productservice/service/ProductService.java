package com.ecommerce.productservice.service;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<String> uploadCSV(FilePart filePart);
}
