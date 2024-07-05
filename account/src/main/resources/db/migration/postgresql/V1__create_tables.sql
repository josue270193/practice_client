CREATE SCHEMA IF NOT EXISTS practice;

CREATE TABLE practice.ACCOUNTS
(
    id               VARCHAR(255) PRIMARY KEY NOT NULL,
    client_id        VARCHAR(255)             NOT NULL,
    number           VARCHAR(50) UNIQUE       NOT NULL,
    type             VARCHAR(50)              NOT NULL,
    last_transaction VARCHAR(255)             NULL,
    initial_balance  DECIMAL(10, 2)           NOT NULL,
    status           VARCHAR(50)              NOT NULL
);

CREATE TABLE practice.TRANSACTIONS
(
    id         VARCHAR(255) PRIMARY KEY NOT NULL,
    account_id VARCHAR(255)             NOT NULL,
    date       TIMESTAMP                NOT NULL,
    type       VARCHAR(50)              NOT NULL,
    amount     DECIMAL(10, 2)           NOT NULL,
    balance    DECIMAL(10, 2)           NOT NULL,
    CONSTRAINT account_fk FOREIGN KEY (account_id) REFERENCES practice.ACCOUNTS (id)
);

ALTER TABLE practice.ACCOUNTS
    ADD CONSTRAINT transaction_fk FOREIGN KEY (last_transaction) REFERENCES practice.TRANSACTIONS (id)