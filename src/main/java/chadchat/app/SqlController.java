package chadchat.app;

import chadchat.db.DBConnect;
import chadchat.db.SqlStatements;

import java.sql.SQLException;

public class SqlController {
    DBConnect db = new DBConnect();
    SqlStatements sql = new SqlStatements();

    public void controller() throws SQLException, ClassNotFoundException {
        db.executeUpdate(sql.createUserTable());
    }
}
