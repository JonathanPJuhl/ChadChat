package chadchat.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class SqlStatements {

    public String createUserTable(){

        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("CREATE TABLE [IF NOT EXISTS] users");
        sqlStatement.append("(");
        sqlStatement.append("ID int, ");
        sqlStatement.append("UserName VARCHAR(45), ");
        sqlStatement.append("PRIMARY KEY (ID)");
        sqlStatement.append(");");

        String sql = "CREATE TABLE [IF NOT EXISTS] users(ID int, UserName VARCHAR(45), PRIMARY KEY (ID));";
            return sql;
    }
}
