CREATE TABLE seller (
     id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    contact_number VARCHAR(50),
    verified BOOLEAN
);

