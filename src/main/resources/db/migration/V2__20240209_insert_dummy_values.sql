-- Inserting data into 'users' table
INSERT INTO users (first_name, last_name, email, password, enabled, token_expired)
VALUES
    ('John', 'Doe', 'john.doe@example.com', 'password123', true, false),
    ('Jane', 'Smith', 'jane.smith@example.com', 'securepass', true, false),
    ('Alice', 'Johnson', 'alice.johnson@example.com', 'pass123', false, true);

-- Inserting data into 'roles' table
INSERT INTO roles (name)
VALUES
    ('ADMIN'),
    ('USER'),
    ('GUEST');

-- Inserting data into 'privileges' table
INSERT INTO privileges (name)
VALUES
    ('READ_PRIVILEGE'),
    ('WRITE_PRIVILEGE'),
    ('DELETE_PRIVILEGE');

-- Inserting data into 'users_roles' table
INSERT INTO users_roles (user_id, role_id)
VALUES
    (1, 1), -- John Doe is an ADMIN
    (2, 2), -- Jane Smith is a USER
    (3, 3); -- Alice Johnson is a GUEST

-- Inserting data into 'roles_privileges' table
INSERT INTO roles_privileges (role_id, privilege_id)
VALUES
    (1, 1), -- ADMIN role has READ_PRIVILEGE
    (1, 2), -- ADMIN role has WRITE_PRIVILEGE
    (2, 1), -- USER role has READ_PRIVILEGE
    (2, 2), -- USER role has WRITE_PRIVILEGE
    (3, 3); -- GUEST role has DELETE_PRIVILEGE
