package chadchat.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class SqlStatements {

    public String createUserTable(){

        String sql = "CREATE TABLE IF NOT EXISTS users(ID int, UserName VARCHAR(45) unique, PassWord VARCHAR(45), PRIMARY KEY (ID));";
            return sql;
    }
}
