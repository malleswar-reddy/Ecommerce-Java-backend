package com.ecommerce.inventoryservice.dtos;



import lombok.Data;

@Data
public class InventoryRequest {
    private Long productId;
    private Integer quantity;
}

