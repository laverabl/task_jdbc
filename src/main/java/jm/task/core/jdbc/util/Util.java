package jm.task.core.jdbc.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Util {

    public static Connection getConection() {
        String URL = null;
        String USER = null;
        String PASSWORD = null;

        FileInputStream fis;
        Properties properties = new Properties();

        try {
            fis = new FileInputStream("src/main/java/resources/config.properties");
            properties.load(fis);
            URL = properties.getProperty("db.host");
            USER = properties.getProperty("db.User");
            PASSWORD = properties.getProperty("db.Password");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Connection con = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Failed to connect");
            e.printStackTrace();
        }

        return con;
    }
}
