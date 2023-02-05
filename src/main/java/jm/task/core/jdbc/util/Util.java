package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String NAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    public static Connection connect() {

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
