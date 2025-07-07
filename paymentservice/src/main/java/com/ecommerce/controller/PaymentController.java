package com.ecommerce.controller;

import com.ecommerce.dto.PaymentRequestDto;
import com.ecommerce.dto.PaymentResponseDto;
import com.ecommerce.model.Payment;
import com.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pay")
    public Mono<PaymentResponseDto> makePayment(@RequestBody PaymentRequestDto paymentRequestDto) {
        return paymentService.processPayment(paymentRequestDto);
    }

    @GetMapping("/order/{orderId}")
    public Flux<Payment> getPayments(@PathVariable String orderId) {
        return paymentService.getPaymentsByOrderId(orderId);
    }
}
