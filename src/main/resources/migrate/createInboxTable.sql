CREATE TABLE IF NOT EXISTS inbox(
recipientID int,
message varchar(300),
messageID int unique,
senderID int,
senderName varchar(45);
sendTime timeStamp,
markAsRead TINYINT(1));