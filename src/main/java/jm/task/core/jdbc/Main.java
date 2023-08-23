package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    private static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Иван", "Мудинов", (byte) 21);
        userService.saveUser("Sam", "Мудинов", (byte) 26);
        userService.saveUser("Ann", " Fine", (byte) 11);
        userService.saveUser("Kate", " Fine", (byte) 15);

        for (User user : userService.getAllUsers()) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
