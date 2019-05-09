package util;

import java.sql.*;

public class JdbcUtil {
    // need set the server's timezone otherwise it will throw exception
    private final static String URL = "jdbc:mysql://localhost:3306/paper_system?serverTimezone=GMT%2B8";
    private final static String USER = "root";
    private final static String PASSWORD = "good2739966538";
    private static JdbcUtil instance = new JdbcUtil();

    private JdbcUtil() {
        try {
            // mysql8 driver path is "com.mysql.cj.jdbc.Driver"
            // mysql5 driver path is "com.mysql.jdbc.Driver"
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static JdbcUtil getInstance() {
        return instance;
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void close(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
