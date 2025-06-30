package com.ecommerce.inventoryservice.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockCheckResponse {

    private boolean inStock;
    private String message;
}