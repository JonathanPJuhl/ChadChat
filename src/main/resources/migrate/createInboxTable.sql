CREATE TABLE IF NOT EXISTS inbox(
userID int,
message varchar(300),
messageID int unique,
senderID int,
sendTime timeStamp,
markAsRead TINYINT(1));