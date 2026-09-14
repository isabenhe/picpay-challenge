CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       document VARCHAR(255) NOT NULL UNIQUE,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255),
                       balance DECIMAL(19, 2),
                       user_type VARCHAR(50)
);

CREATE TABLE transactions (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              amount DECIMAL(19, 2),
                              sender_id BIGINT NOT NULL,
                              receiver_id BIGINT NOT NULL,
                              timestamp DATETIME NOT NULL,

                              CONSTRAINT fk_transaction_sender
                                  FOREIGN KEY (sender_id)
                                      REFERENCES users(id),

                              CONSTRAINT fk_transaction_receiver
                                  FOREIGN KEY (receiver_id)
                                      REFERENCES users(id)
);