package chadchat.db;

import chadchat.domain.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SqlStatements {

    public static String checkPassword(User user) {

        String sql = "SELECT PassWord FROM users WHERE UserName='"+user.getName()+"';";
        return sql;
    }

    public String createUserTable(){

        String sql = "CREATE TABLE IF NOT EXISTS users(ID int, UserName VARCHAR(45) unique, PassWord VARCHAR(250), Email VARCHAR(45), PRIMARY KEY (ID));";
        return sql;
    }
    public String createInboxTable(){
        String sql = "CREATE TABLE IF NOT EXISTS inbox("+
                "userID int, " +
                "message varchar(300), " +
                "messageID int unique, " +
                "senderID int, " +
                "timeStamp DATE, " +
                "markAsRead TINYINT(1));";
        return sql;
    }
    public static String getAllIds(){
        String sql = "SELECT ID FROM users";
        return sql;
    }
    public static String doesUsernameAlreadyExist(String username){
        String sql = "SELECT * FROM users WHERE userName = '" + username + "'";
        return sql;
    }
    public static String doesEmailAlreadyExist(String eMail){
        String sql = "SELECT * FROM users WHERE Email = '" + eMail + "'";
        return sql;
    }
    public static String insertUserIntoDB(User user){
        String sql = "INSERT INTO users VALUES (" + user.getId() +
                ", '" + user.getName() + "'" +
                ", '" + user.getPassword() + "'" +
                ", '" + user.getMail() + "'" +
                ");";
        return sql;
    }
    public static String findUserIdFromUserName(String userName){
        String sql = "SELECT ID FROM users WHERE UserName = '" + userName +"';";
        return sql;
    }

    public static String sendUserAMessage(String message, User user, int recipientId){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(format);
        String sql = "INSERT INTO inbox(recipientID, message, senderID, senderName, sendTime, markAsRead) VALUES ("
                + recipientId +
                ", '" + message + "'" +
                "," + user.getId() +
                ", '" + user.getName() + "'" +
                ", '" + formatDateTime + "'" +
                ", 0);";
        System.out.println(sql);
        return sql;
    }
    public static String readMyMessages(int id){
        String sql = "SELECT senderName, message, sendTime FROM inbox WHERE recipientID = '"+id+"';";
        return sql;
    }
    /*CREATE TABLE IF NOT EXISTS inbox(
            recipientID int,
            message varchar(300),
    messageID int unique,
    senderID int,
    senderName varchar(45);
    sendTime timeStamp,
    markAsRead TINYINT(1));*/
}
