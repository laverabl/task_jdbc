package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String  str = "CREATE TABLE IF NOT EXISTS USERS"
                + "(id INT PRIMARY KEY AUTO_INCREMENT,"
                + "Name VARCHAR(20), "
                + "Lastname VARCHAR(45),"
                + "Age INT(3))";


        try(Statement st = connection.createStatement() ) {
            connection.setAutoCommit(false);
            st.executeUpdate(str);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void dropUsersTable() {
        String  str = "DROP TABLE IF EXISTS USERS";

        try (PreparedStatement preparedStatement = connection.prepareStatement(str)) {
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String  str = "INSERT  INTO USERS (NAME,LASTNAME, AGE) values (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(str)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name );
            preparedStatement.setString(2, lastName );
            preparedStatement.setByte(3, age );
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.printf("User c именем - %s добавлен в базу данных\n", name );
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void removeUserById(long id) {
        String  str = "DELETE FROM USERS WHERE ID=?";

        try( PreparedStatement preparedStatement = connection.prepareStatement(str) ) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public List<User> getAllUsers() {
        String  str = "SELECT * FROM USERS";
        List<User>  userList = new ArrayList<>();

        try(ResultSet resultUser = connection.createStatement().executeQuery(str)) {
            while (resultUser.next()) {
                User user = new User(resultUser.getString("NAME"), resultUser.getString("LASTNAME"), resultUser.getByte("AGE"));
                user.setId(resultUser.getLong("ID"));
                userList.add(user);
            }
        } catch (SQLException e) {
           throw  new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        String  str = "TRUNCATE TABLE USERS";

        try (PreparedStatement preparedStatement = connection.prepareStatement(str)) {
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
