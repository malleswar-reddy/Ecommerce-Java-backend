CREATE TABLE product (
  id SERIAL PRIMARY KEY,
  user_id TEXT,
  product_id TEXT,
  product_url TEXT,
  image_url TEXT,
  price TEXT,
  rating INT,
  timestamp BIGINT
);
