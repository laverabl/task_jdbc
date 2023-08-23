package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        System.out.println("таблица создана");
        userService.saveUser("Barak", "Obama", (byte) 56);
        userService.saveUser("Barak1", "Obama", (byte) 516);
        userService.saveUser("Barak2", "Obama", (byte) 526);
        userService.saveUser("Barak3", "Obama", (byte) 536);
        for (User user: userService.getAllUsers()) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
