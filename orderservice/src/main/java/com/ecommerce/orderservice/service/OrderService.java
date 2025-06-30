package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.entity.Orders;

import java.util.List;

public interface OrderService {
    Orders saveOrder(Orders order);
    List<Orders> getAllOrders();
    Orders getOrderById(Long id);
    void deleteOrder(Long id);
}
