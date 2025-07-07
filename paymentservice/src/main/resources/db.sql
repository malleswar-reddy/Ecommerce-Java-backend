CREATE TABLE payments (
    id SERIAL PRIMARY KEY,
    order_id VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    payment_method VARCHAR(50),
    payment_status VARCHAR(50),
    transaction_id VARCHAR(255),
    payment_date TIMESTAMP,
    updated_at TIMESTAMP
);
