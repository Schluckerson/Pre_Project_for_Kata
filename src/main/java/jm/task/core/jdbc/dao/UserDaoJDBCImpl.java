package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users"
                + "(id INTEGER NOT NULL AUTO_INCREMENT, "
                + "name VARCHAR(45) NOT NULL, "
                + "lastname VARCHAR(45) NOT NULL, "
                + "age TINYINT NOT NULL, "
                + "PRIMARY KEY (id))";
        try(Connection connection = Util.connect(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try(Connection connection = Util.connect(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users(name, lastname, age) VALUES (?, ?, ?)";
        try(Connection connection = Util.connect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try(Connection connection = Util.connect(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try(Connection connection = Util.connect(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                Byte age = resultSet.getByte(4);
                users.add(new User(name, lastName, age));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE users";
        try(Connection connection = Util.connect(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
