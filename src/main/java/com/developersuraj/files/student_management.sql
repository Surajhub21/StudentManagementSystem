-- ============================================
-- Student Management System Database Script
-- PostgreSQL
-- ============================================

-- Create Database
CREATE DATABASE student_management;

-- Connect to the database before running the remaining script.
-- In psql:
-- \c student_management


-- ============================================
-- Students Table
-- ============================================

CREATE TABLE students (
                          student_id INT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          age INT NOT NULL CHECK (age >= 16 AND age <= 100),
                          course VARCHAR(100) NOT NULL
);


-- ============================================
-- Users Table
-- ============================================

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(20) NOT NULL,
                       student_id INT,

                       CONSTRAINT fk_student
                           FOREIGN KEY (student_id)
                               REFERENCES students(student_id)
                               ON DELETE SET NULL
);


-- ============================================
-- Sample Student Data
-- ============================================

INSERT INTO students (student_id, name, age, course)
VALUES
    (101, 'Suraj Mondal', 22, 'Computer Science'),
    (102, 'Rahul Sharma', 21, 'Information Technology'),
    (103, 'Priya Singh', 20, 'Electronics');


-- ============================================
-- Sample Users
-- Passwords are plain text for now.
-- Replace with SHA-256 hashes later.
-- ============================================

INSERT INTO users (username, password, role, student_id)
VALUES
    ('admin', 'admin123', 'ADMIN', NULL),
    ('suraj', '123', 'STUDENT', 101),
    ('rahul', '123', 'STUDENT', 102),
    ('priya', '123', 'STUDENT', 103);


-- ============================================
-- Verify Data
-- ============================================

SELECT * FROM students;

SELECT * FROM users;

--
--
--

INSERT INTO users(username,password,role,student_id)
VALUES
    ('admin',
     '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9',
     'ADMIN',
     NULL),

    ('suraj',
     'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',
     'STUDENT',
     101);