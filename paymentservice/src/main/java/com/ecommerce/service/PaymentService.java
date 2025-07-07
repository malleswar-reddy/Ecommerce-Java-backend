package com.ecommerce.service;

import com.ecommerce.client.PaymentGatewayClient;
import com.ecommerce.dto.PaymentRequestDto;
import com.ecommerce.dto.PaymentResponseDto;
import com.ecommerce.model.Payment;
import com.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentGatewayClient paymentGatewayClient;

    public Mono<PaymentResponseDto> processPayment(PaymentRequestDto requestDto) {
        Payment payment = new Payment();
        payment.setOrderId(requestDto.getOrderId());
        payment.setUserId(requestDto.getUserId());
        payment.setAmount(requestDto.getAmount());
        payment.setPaymentMethod(requestDto.getPaymentMethod());
        payment.setPaymentStatus("PENDING");
        payment.setPaymentDate(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());

        return paymentGatewayClient.process(requestDto)
                .flatMap(transactionId -> {
                    payment.setTransactionId(transactionId);
                    payment.setPaymentStatus("SUCCESS");
                    payment.setUpdatedAt(LocalDateTime.now());
                    return paymentRepository.save(payment);
                })
                .map(saved -> new PaymentResponseDto(
                        saved.getId(), saved.getOrderId(), saved.getPaymentStatus(),
                        saved.getTransactionId(), saved.getAmount(), saved.getPaymentDate()
                ));
    }

    public Flux<Payment> getPaymentsByOrderId(String orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
}
