CREATE TABLE IF NOT EXISTS users(
ID int NOT NULL unique,
Username varchar(45) unique,
Pass VARCHAR (64),
Email VARCHAR(45) unique,
primary key (ID)
);