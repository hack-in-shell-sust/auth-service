CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    enabled BOOLEAN,
    token_expired BOOLEAN
);

CREATE SEQUENCE users_seq START 1;

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

CREATE SEQUENCE roles_seq START 1;

CREATE TABLE privileges (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

CREATE SEQUENCE privileges_seq START 1;

CREATE TABLE users_roles (
    user_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE roles_privileges (
    role_id BIGINT,
    privilege_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES roles (id),
    FOREIGN KEY (privilege_id) REFERENCES privileges (id),
    PRIMARY KEY (role_id, privilege_id)
);
