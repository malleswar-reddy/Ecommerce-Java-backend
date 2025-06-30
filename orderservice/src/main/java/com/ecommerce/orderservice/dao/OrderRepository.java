package com.ecommerce.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ecommerce.orderservice.entity.Orders;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

}
