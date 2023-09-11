package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Barak", "Obama", (byte) 56);
        userService.saveUser("Barak1", "Obama", (byte) 516);
        userService.saveUser("Alice", "Obama", (byte) 526);
        userService.saveUser("Elvin", "808", (byte) 536);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
