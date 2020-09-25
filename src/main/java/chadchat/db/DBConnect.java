package chadchat.db;


import java.sql.*;
import java.util.ArrayList;

public class DBConnect {

    // Don't need JDBC driver in java 4+
    //final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/chadchatDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    //  Database credentials
    static final String USER = "chadchatDB";
    static final String PASS = null;

    private static final int version = 2;

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public ResultSet executeQuery(String sql) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        PreparedStatement sqlState = conn.prepareStatement(sql);
        ResultSet rs = sqlState.executeQuery(sql);
        return rs;
    }
    public int executeUpdate(String sql) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement sqlState = conn.prepareStatement(sql);
        int amountUpdated = sqlState.executeUpdate(sql);
       return amountUpdated;
    }

    public int getLatestIDFromDB(String sql) throws SQLException, ClassNotFoundException {
        int id = 0;
        //Create a new arraylist to later fill with id's
        ArrayList<Integer> idArr = new ArrayList<>();

        ResultSet rs  = executeQuery(sql);

        //Adds each id to the list
        while(rs.next()){
            idArr.add(rs.getInt("ID"));
        }
        //Make id equal to amount of id's so that it will have the same value as latest id
        id = idArr.size();
        //Add one on return, so that this new id will be the highest yet.
        return id+1;
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
