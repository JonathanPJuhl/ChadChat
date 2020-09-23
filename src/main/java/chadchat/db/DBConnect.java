package chadchat.db;


import java.sql.*;

public class DBConnect {

    // Don't need JDBC driver in java 4+
    //final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/chadchadtest?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "12345678";

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public ResultSet executeQuery(String sql, Connection conn) throws ClassNotFoundException, SQLException {
        PreparedStatement sqlState = conn.prepareStatement(sql);
        ResultSet rs = sqlState.executeQuery(sql);
        return rs;
    }
    public int executeUpdate(String sql, Connection conn) throws SQLException {
        PreparedStatement sqlState = conn.prepareStatement(sql);
        int amountUpdated = sqlState.executeUpdate(sql);
       return amountUpdated;
    }


}
