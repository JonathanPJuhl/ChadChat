CREATE TABLE IF NOT EXISTS users(
ID int NOT NULL unique,
UserName varchar(45) unique,
PassWord VARCHAR (64),
Email VARCHAR(45) unique,
primary key (ID)
);