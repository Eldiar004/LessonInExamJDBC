package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static String url = "jdbc:postgresql://localhost:5432/java7";
    private static String userName  = "postgres";
    private static String password= "1234";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url , userName , password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
