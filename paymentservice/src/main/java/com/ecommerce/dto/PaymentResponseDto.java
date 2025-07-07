package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {
    private Long id;
    private String orderId;
    private String paymentStatus;
    private String transactionId;
    private Double amount;
    private LocalDateTime paymentDate;
}
