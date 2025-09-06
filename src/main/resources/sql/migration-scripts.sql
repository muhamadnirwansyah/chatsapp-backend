--migration scripts--
CREATE TABLE roles(
     id BIGSERIAL PRIMARY KEY,
     name VARCHAR(100) NOT NULL
);

CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    phone_number VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL, -- bcrypt hash
    role_id BIGINT REFERENCES roles(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE message(
    id SERIAL PRIMARY KEY,
    sender_id BIGINT REFERENCES users(id),
    receive_id BIGINT REFERENCES users(id),
    content TEXT NOT NULL,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'SENT' -- SENT, DELIVERED, READ
);