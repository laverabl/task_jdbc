package jm.task.core.jdbc.util;
import java.sql.*;

public class Util {

    public static Connection getConection() {
        Connection con = null;
        String URL = "jdbc:mysql://localhost:3306/my_first_db";
        String USER = "root";
        String PASSWORD = "12345678";

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Failed to connect");
            e.printStackTrace();
        }

        return con;
    }
}
