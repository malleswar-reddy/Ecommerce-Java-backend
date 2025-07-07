package com.ecommerce.client;

import com.ecommerce.dto.PaymentRequestDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class RazorpayPaymentGatewayClient implements PaymentGatewayClient {

    @Override
    public Mono<String> process(PaymentRequestDto requestDto) {
        // Mock Razorpay logic (replace with real SDK calls)
        String transactionId = UUID.randomUUID().toString();
        return Mono.just(transactionId);
    }
}
