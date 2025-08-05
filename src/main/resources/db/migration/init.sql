CREATE TABLE donor (
    email VARCHAR PRIMARY KEY,
    full_name VARCHAR NOT NULL
);

CREATE TABLE beneficiary (
    email VARCHAR PRIMARY KEY,
    full_name VARCHAR NOT NULL
);

CREATE TABLE payment (
    id VARCHAR PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    payment_method VARCHAR NOT NULL,
    status VARCHAR NOT NULL
);

CREATE TABLE donation (
    id SERIAL PRIMARY KEY,
    donor_email VARCHAR REFERENCES donor(email),
    payment_id VARCHAR REFERENCES payment(id)
);

CREATE TABLE help (
    id SERIAL PRIMARY KEY,
    beneficiary_email VARCHAR REFERENCES beneficiary(email),
    payment_id VARCHAR REFERENCES payment(id),
    accident_description TEXT
);