package com.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("payments")
public class Payment {

    @Id
    private Long id;

    private String orderId;
    private String userId;
    private Double amount;
    private String paymentMethod; // Example: CARD, UPI, WALLET, COD
    private String paymentStatus; // Example: SUCCESS, FAILED, PENDING

    private String transactionId;
    private LocalDateTime paymentDate;
    private LocalDateTime updatedAt;

}
