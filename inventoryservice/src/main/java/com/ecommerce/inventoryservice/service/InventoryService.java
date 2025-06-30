package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dtos.StockCheckResponse;
import com.ecommerce.inventoryservice.model.Inventory;

public interface InventoryService {
    Inventory addOrUpdateStock(Long productId, Integer quantity);
    Inventory getStock(Long productId);
    Inventory reduceStock(Long productId, Integer quantity);
    StockCheckResponse isInStock(Long productId, Integer requiredQuantity);
}
