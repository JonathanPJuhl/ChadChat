package chadchat.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

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
}
