package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dao.ProductRepository;
import com.ecommerce.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Mono<Product> saveProduct(Product product) {
        return repository.save(product);
    }



    @Override
    public Flux<Product> getAllProducts() {
        return repository.findAll();
    }


    @Override
    public Flux<Product> saveAllFromCSV(FilePart filePart) {
        return filePart.content()
                .map(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);
                    return new String(bytes);
                })
                .collectList()
                .flatMapMany(lines -> {
                    List<Product> products = new ArrayList<>();
                    String[] csvLines = String.join("", lines).split("\n");

                    for (int i = 1; i < csvLines.length; i++) {
                        String[] fields = csvLines[i].split(",");

                        if (fields.length >= 4) {
                            Product p = new Product();
                            p.setName(fields[0].trim());
                            p.setDescription(fields[1].trim());
                            p.setPrice(Double.parseDouble(fields[2].trim()));
                            p.setQuantity(Integer.parseInt(fields[3].trim()));
                            products.add(p);
                        }
                    }
                    return repository.saveAll(products);
                });
    }
}
