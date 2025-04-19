DROP TABLE IF EXISTS member;
create table member (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userId VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
