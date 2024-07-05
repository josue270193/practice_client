CREATE SCHEMA IF NOT EXISTS practice;

CREATE TABLE practice.PERSONS
(
    id              VARCHAR(255) PRIMARY KEY NOT NULL,
    document_number VARCHAR(50) UNIQUE       NOT NULL,
    name            VARCHAR(255)             NOT NULL,
    gender          VARCHAR(50)              NOT NULL,
    born_date       TIMESTAMP                NOT NULL,
    address         VARCHAR(255)             NOT NULL,
    phone_number    VARCHAR(50)              NOT NULL
);

CREATE TABLE practice.CLIENTS
(
    id       VARCHAR(255) PRIMARY KEY NOT NULL,
    password VARCHAR(255)             NOT NULL,
    status   VARCHAR(50)              NOT NULL,
    CONSTRAINT person_fk FOREIGN KEY (id) REFERENCES practice.PERSONS (id)
);