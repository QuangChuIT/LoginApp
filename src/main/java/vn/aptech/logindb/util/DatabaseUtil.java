package vn.aptech.logindb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/logindb?useSSL=false";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "123abc@A";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Error connect to database: " + e.getMessage());
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        Connection connection = DatabaseUtil.getConnection();
        if (connection != null) {
            System.out.println("connect success");
        } else {
            System.out.println("Connect fail");
        }
    }
}
