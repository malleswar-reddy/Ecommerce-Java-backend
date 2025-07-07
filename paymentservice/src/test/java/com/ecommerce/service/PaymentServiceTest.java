package com.ecommerce.service;

import com.ecommerce.client.PaymentGatewayClient;
import com.ecommerce.dto.PaymentRequestDto;
import com.ecommerce.model.Payment;
import com.ecommerce.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentGatewayClient paymentGatewayClient;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void testProcessPayment_Success() {
        PaymentRequestDto requestDto = new PaymentRequestDto("order123", "user456", 100.0, "CARD");
        String mockTransactionId = "txn_abc123";

        Payment savedPayment = new Payment(
                1L, requestDto.getOrderId(), requestDto.getUserId(), requestDto.getAmount(),
                requestDto.getPaymentMethod(), "SUCCESS", mockTransactionId, LocalDateTime.now(), LocalDateTime.now()
        );

        when(paymentGatewayClient.process(requestDto)).thenReturn(Mono.just(mockTransactionId));
        when(paymentRepository.save(any(Payment.class))).thenReturn(Mono.just(savedPayment));

        StepVerifier.create(paymentService.processPayment(requestDto))
                .expectNextMatches(response -> response.getPaymentStatus().equals("SUCCESS"))
                .verifyComplete();

        verify(paymentRepository, times(1)).save(any(Payment.class));
    }
}
