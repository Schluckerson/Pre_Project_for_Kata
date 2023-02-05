package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static SessionFactory connectHibernate() {
        Properties properties = new Properties();
        properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(AvailableSettings.URL, URL);
        properties.put(AvailableSettings.USER, NAME);
        properties.put(AvailableSettings.PASS, PASSWORD);
        properties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");

        Configuration configuration = new Configuration();
        configuration.addProperties(properties);
        configuration.addAnnotatedClass(User.class);
        return configuration.buildSessionFactory();
    }
}
