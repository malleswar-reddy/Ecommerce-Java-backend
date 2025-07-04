package com.ecommerce.client;

import com.ecommerce.dto.PaymentRequestDto;
import reactor.core.publisher.Mono;

public interface PaymentGatewayClient {
    Mono<String> process(PaymentRequestDto requestDto);
}
