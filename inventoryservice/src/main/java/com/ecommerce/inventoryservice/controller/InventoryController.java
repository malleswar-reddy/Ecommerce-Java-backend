package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.dtos.InventoryRequest;
import com.ecommerce.inventoryservice.dtos.StockCheckResponse;
import com.ecommerce.inventoryservice.model.Inventory;
import com.ecommerce.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/add")
    public Inventory addStock(@RequestBody InventoryRequest request) {
        return inventoryService.addOrUpdateStock(request.getProductId(), request.getQuantity());
    }

    @GetMapping("/{productId}")
    public Inventory getStock(@PathVariable Long productId) {
        return inventoryService.getStock(productId);
    }

    @PostMapping("/reduce")
    public Inventory reduceStock(@RequestBody InventoryRequest request) {
        return inventoryService.reduceStock(request.getProductId(), request.getQuantity());
    }

    @PostMapping("/check")
    public StockCheckResponse checkStockAvailability(@RequestBody InventoryRequest request) {
        return inventoryService.isInStock(request.getProductId(), request.getQuantity());
    }
}
