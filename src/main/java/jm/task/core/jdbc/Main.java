package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl action = new UserServiceImpl();
        action.createUsersTable();

        action.saveUser("1name", "1lastname", (byte)20);
        action.saveUser("2name", "2lastname", (byte)20);
        action.saveUser("3name", "3lastname", (byte)20);
        action.saveUser("4name", "4lastname", (byte)20);

        List<User> users = action.getAllUsers();
        users.forEach(x -> System.out.println(x.toString()));

        action.cleanUsersTable();
        action.dropUsersTable();
    }
}

