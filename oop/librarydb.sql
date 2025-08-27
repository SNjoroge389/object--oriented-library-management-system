CREATE DATABASE IF NOT EXISTS librarydb;
USE librarydb;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(50) NOT NULL
);

INSERT INTO users (username, password) VALUES ('admin', '1234');

DROP TABLE IF EXISTS books;
CREATE TABLE books (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  author VARCHAR(100) NOT NULL,
  year INT NOT NULL,
  isbn VARCHAR(20) UNIQUE
);

INSERT INTO books (title, author, year, isbn)
VALUES 
('Introduction to Algorithms', 'Cormen', 2009, '9780262033848'),
('Clean Code', 'Robert C. Martin', 2008, '9780132350884');
