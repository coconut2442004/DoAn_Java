DROP DATABASE BOOK_DB;

-- Tạo database
CREATE DATABASE BOOK_DB;

USE BOOK_DB;

-- Tạo bảng lưu trữ thông tin về sách
CREATE TABLE Book(
    Book_code VARCHAR(12),
    Book_title VARCHAR(255),
    Author VARCHAR(255),
    Status VARCHAR(1),
    PRIMARY KEY (Book_code)
);

-- Tạo bảng lưa trữ thông tin về tài khoản đăng nhập
CREATE TABLE Account(
    Username VARCHAR(20),
    Password VARCHAR(20),
    PRIMARY KEY (Username)
);

-- Tạo bảng phiếu mượn
CREATE TABLE BorrowTicket (
    Borrow_ticket_code VARCHAR(20),
    Reader VARCHAR(255),
    Book_code VARCHAR(12),
    Status VARCHAR(1),
    Borrow_date Date,
    Return_date Date,
    PRIMARY KEY (Borrow_ticket_code),
    FOREIGN KEY (Book_code) REFERENCES Book(Book_code)
);

-- Ràng buộc dữ liệu
ALTER TABLE
    Book
ADD
    CONSTRAINT Check_Status CHECK (
        Status = "1"
        OR Status = "0"
    );

-- Ràng buộc dữ liệu
ALTER TABLE
    BorrowTicket
ADD
    CONSTRAINT Check_Status CHECK (
        Status = "1"
        OR Status = "0"
    );

--  Chèn dữ liệu vào database
INSERT INTO
    Book (Book_code, Book_title, Author, Status)
VALUES
    (
        "B001",
        "Introduction to SQL",
        "Rick F.van der Lans",
        "0"
    ),
    (
        "B002",
        "Python Programming",
        "Fabrizio Romano",
        "0"
    ),
    (
        "B003",
        "Data Science Handbook",
        "Field Cady",
        "0"
    ),
    (
        "B004",
        "Web Development Basics",
        "Andy Python",
        "0"
    ),
    (
        "B005",
        "Web Development Basics",
        "Brett Spell",
        "0"
    ),
    (
        "B006",
        "Machine Learning Essentials",
        "Anurag Bhardwaj, Wei Di, Jianing Wei",
        "0"
    ),
    (
        "B007",
        "Network Security Fundamentals",
        "Eric Cole, Ronald L. Krutz, James Conley",
        "0"
    ),
    ("B008", "C++ Programming", "Steve Oualline", "0"),
    (
        "B009",
        "Database Management Systems",
        "Mukesh Negi",
        "0"
    );

INSERT INTO
    Account(Username, Password)
VALUES
    ("admin", "admin");

INSERT INTO
    BorrowTicket(
        Borrow_ticket_code,
        Reader,
        Book_code,
        Status,
        Borrow_date,
        Return_date
    )
VALUES
    (
        "BT001",
        "Nguyen Tien Dat",
        "B001",
        "0",
        "2023-04-24",
        "2023-05-01"
    );