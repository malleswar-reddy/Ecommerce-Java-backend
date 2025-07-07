package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dao.ProductRepository;
import com.ecommerce.productservice.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override

    public Mono<String> uploadCSV(FilePart filePart) {
        AtomicBoolean isFirstLine = new AtomicBoolean(true);

        return filePart.content()
                .flatMap(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    String content = new String(bytes, StandardCharsets.UTF_8);
                    return Flux.fromArray(content.split("\n"));
                })
                .filter(line -> {
                    if (isFirstLine.get()) {
                        isFirstLine.set(false);
                        return false;
                    }
                    return true;
                })
                .flatMap(line -> {
                    Product product = convertLineToProduct(line);
                    return product != null ? productRepository.save(product) : Mono.empty();
                })
                .then(Mono.just("Upload Successful"));
    }


    private Product convertLineToProduct(String line) {
        try {
            String[] fields = line.split(",");

            if (fields.length < 7) {
                log.error("Skipping invalid CSV line (not enough columns): {}", line);
                return null;
            }

            Product product = new Product();
            product.setUserId(fields[0].trim());
            product.setProductId(fields[1].trim());
            product.setProductUrl(fields[2].trim());
            product.setImageUrl(fields[3].trim());
            product.setPrice(fields[4].trim());
            product.setRating(parseInt(fields[5], "rating"));
            product.setTimestamp(Long.parseLong(fields[6].trim()));

            return product;

        } catch (Exception ex) {
            log.error("Failed to parse line: {}", line, ex);
            return null;
        }
    }


    private int parseInt(String field, String fieldName) {
        try {
            return Integer.parseInt(field.trim());
        } catch (NumberFormatException e) {
            log.warn("Invalid integer for {}: '{}', defaulting to 0", fieldName, field.trim());
            return 0;
        }
    }

    private double parseDouble(String field, String fieldName) {
        try {
            return Double.parseDouble(field.trim().replace("₹", ""));
        } catch (NumberFormatException e) {
            log.warn("Invalid double for {}: '{}', defaulting to 0.0", fieldName, field.trim());
            return 0.0;
        }
    }
}



























//package com.ecommerce.productservice.service;
//
//import com.ecommerce.productservice.dao.ProductRepository;
//import com.ecommerce.productservice.model.Product;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;
//import org.springframework.core.io.buffer.DataBufferUtils;
//import org.springframework.http.codec.multipart.FilePart;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.nio.charset.StandardCharsets;
//
//@Service
//@RequiredArgsConstructor
//public class ProductServiceImpl implements ProductService {
//
//    private final ProductRepository productRepository;
//
//    public Mono<String> uploadCSV(FilePart filePart) {
//        return DataBufferUtils.join(filePart.content())
//                .flatMapMany(dataBuffer -> {
//                    BufferedReader reader = new BufferedReader(
//                            new InputStreamReader(dataBuffer.asInputStream(), StandardCharsets.UTF_8));
//
//                    try {
//                        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT
//                                .withHeader()
//                                .withSkipHeaderRecord());
//
//                        return Flux.fromIterable(parser.getRecords())
//                                .map(this::toProduct)
//                                .flatMap(productRepository::save);
//                    } catch (Exception e) {
//                        return Flux.error(e);
//                    }
//                })
//                .then(Mono.just("Upload Successful"));
//    }
//
//    private Product toProduct(CSVRecord record) {
//        Product product = new Product();
//        product.setUserId(record.get("user_id"));
//        product.setProductId(record.get("product_id"));
//        product.setProductUrl(record.get("product_url"));
//        product.setImageUrl(record.get("image_url"));
//        product.setPrice(record.get("price"));
//        product.setRating(Double.parseDouble(record.get("rating")));
//
//        product.setTimestamp(Long.parseLong(record.get("timestamp")));
//        return product;
//    }
//}
