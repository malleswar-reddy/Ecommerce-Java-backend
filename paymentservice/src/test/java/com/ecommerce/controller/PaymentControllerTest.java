package com.ecommerce.controller;

import com.ecommerce.dto.PaymentRequestDto;
import com.ecommerce.dto.PaymentResponseDto;
import com.ecommerce.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebFluxTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PaymentService paymentService;

    @Test
    void makePayment_ShouldReturnSuccess() {
        PaymentRequestDto requestDto = new PaymentRequestDto("order123", "user456", 100.0, "CARD");
        PaymentResponseDto responseDto = new PaymentResponseDto(
                1L, "order123", "SUCCESS", "txn_12345", 100.0, LocalDateTime.now()
        );

        when(paymentService.processPayment(any())).thenReturn(Mono.just(responseDto));

        webTestClient.post()
                .uri("/api/payments/pay")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.paymentStatus").isEqualTo("SUCCESS");

        verify(paymentService, times(1)).processPayment(any());
    }
}