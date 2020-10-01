CREATE TABLE IF NOT EXISTS users(
ID int NOT NULL unique,
UserName varchar(45) unique,
PassWord VARCHAR (64),
Email VARCHAR(45) unique,
primary key (ID)
);

CREATE TABLE IF NOT EXISTS inbox(
recipientID int,
message varchar(300),
messageID int unique,
senderID int,
senderName varchar(45);
sendTime timeStamp,
markAsRead TINYINT(1));

UPDATE properties
SET value = '1'
WHERE name = "version";