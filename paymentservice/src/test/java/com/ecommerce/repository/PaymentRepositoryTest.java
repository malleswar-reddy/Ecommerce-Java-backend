package com.ecommerce.repository;

import com.ecommerce.model.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@DataR2dbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    void testSaveAndFind() {
        Payment payment = new Payment(null, "order123", "user123", 99.99,
                "CARD", "SUCCESS", "txn987", LocalDateTime.now(), LocalDateTime.now());

        StepVerifier.create(paymentRepository.save(payment))
                .expectNextMatches(saved -> saved.getOrderId().equals("order123"))
                .verifyComplete();
    }
}

