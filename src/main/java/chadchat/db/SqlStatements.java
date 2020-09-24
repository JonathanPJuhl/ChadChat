package chadchat.db;

import chadchat.domain.User;

public class SqlStatements {

    public String createUserTable(){

        String sql = "CREATE TABLE IF NOT EXISTS users(ID int, UserName VARCHAR(45) unique, PassWord VARCHAR(45), Email VARCHAR(45), PRIMARY KEY (ID));";
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
        String sql = "SELECT * FROM users WHERE username = '" + username + "'";
        return sql;
    }
    public static String doesEmailAlreadyExist(String eMail){
        String sql = "SELECT * FROM users WHERE Email = '" + eMail + "'";
        return sql;
    }
    public static String insertUserIntoDB(User user){
        String sql = "INSERT INTO users VALUES (" + user.getId() +
                ", " + user.getName() +
                ", " + user.getPassword() +
                ", " + user.getMail() +
                ");";
        return sql;
    }
}
