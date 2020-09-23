package chadchat.db;


import java.sql.*;

public class DBConnect {
    // JDBC driver name and database URL
    //final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/chadchadtest?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "12345678";



    /*public void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            conn2 = conn;
        }
    }*/

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public ResultSet executeQuery(String sql, Connection conn) throws ClassNotFoundException, SQLException {
        PreparedStatement sqlState = conn.prepareStatement(sql);
        ResultSet rs = sqlState.executeQuery(sql);
        //qlState.
        return rs;
    }

}
