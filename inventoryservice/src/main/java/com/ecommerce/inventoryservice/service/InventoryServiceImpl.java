package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dtos.StockCheckResponse;
import com.ecommerce.inventoryservice.model.Inventory;
import com.ecommerce.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory addOrUpdateStock(Long productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElse(Inventory.builder()
                        .productId(productId)
                        .quantity(0)
                        .build());
        inventory.setQuantity(inventory.getQuantity() + quantity);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory getStock(Long productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Inventory reduceStock(Long productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (inventory.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }
        inventory.setQuantity(inventory.getQuantity() - quantity);
        return inventoryRepository.save(inventory);
    }

    @Override
    public StockCheckResponse isInStock(Long productId, Integer requiredQuantity) {
        return inventoryRepository.findByProductId(productId)
                .map(inv -> {
                    boolean available = inv.getQuantity() >= requiredQuantity;
                    String msg = available
                            ? "Stock available for product ID " + productId
                            : "Insufficient stock for product ID " + productId;
                    return new StockCheckResponse(available, msg);
                })
                .orElseGet(() -> new StockCheckResponse(false, "Product not found with ID " + productId));
    }
}
