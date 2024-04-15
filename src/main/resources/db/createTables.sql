-- Create roles table
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

-- Create users table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE users_roles (
    user_entity_id INT,
    roles_id INT,
    PRIMARY KEY (user_entity_id, roles_id),
    FOREIGN KEY (user_entity_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (roles_id) REFERENCES roles (id) ON DELETE CASCADE
);

-- Insert roles
INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'VIEWER');
INSERT INTO roles (id, name) VALUES (3, 'EDITOR');


-- Insert a user: password123
INSERT INTO users (id, username, password) VALUES (1, 'forescout', '$2a$10$ChSjxqGHLsn8c6wkGVDcRex3nVkRiVj5ksSnoQJk/Xb9eAHFdMJPW');
INSERT INTO users (id, username, password) VALUES (2, 'admin', '$2a$10$ChSjxqGHLsn8c6wkGVDcRex3nVkRiVj5ksSnoQJk/Xb9eAHFdMJPW');

-- Assign roles to the user
INSERT INTO users_roles (user_entity_id, roles_id) VALUES (1, 1);
INSERT INTO users_roles (user_entity_id, roles_id) VALUES (2, 3);
