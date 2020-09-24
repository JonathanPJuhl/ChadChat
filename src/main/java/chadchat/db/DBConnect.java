package chadchat.db;


import java.sql.*;

public class DBConnect {

    // Don't need JDBC driver in java 4+
    //final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/chadchatdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "997865root";

    private static final int version = 2;

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
    public static int getCurrentVersion() {
        try (Connection conn = getConnection()) {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT value FROM properties WHERE name = 'version';");
            if(rs.next()) {
                String column = rs.getString("value");
                return Integer.parseInt(column);
            } else {
                System.err.println("No version in properties.");
                return -1;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }
    public static int getVersion() {
        return version;
    }


}
