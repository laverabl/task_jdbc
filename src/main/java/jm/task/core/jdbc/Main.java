package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    private static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Иван", "Ivanov", (byte) 21);
        userService.saveUser("Sam", "Sam", (byte) 26);
        userService.saveUser("Ann", " Fine", (byte) 11);
        userService.saveUser("Kate", " Fine", (byte) 15);

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
