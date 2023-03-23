package dev.roy.todoapp.dao;

import dev.roy.todoapp.model.User;
import dev.roy.todoapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private static final String INSERT_USERS_SQL = "INSERT INTO users " +
            "(first_name, last_name, username, password) " +
            "VALUES " +
            "(?, ?, ?, ?)";
    private static final String CHECK_USERNAME = "SELECT id FROM users WHERE username = ?";

    public boolean checkDuplicateUsername(String username) {
        boolean duplicate = false;

        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            duplicate = resultSet.next();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return duplicate;
    }

    public int registerEmployee(User employee) throws ClassNotFoundException {
        int result = 0;

            try(Connection connection = JDBCUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
                preparedStatement.setString(1, employee.getFirstName());
                preparedStatement.setString(2, employee.getLastName());
                preparedStatement.setString(3, employee.getUsername());
                preparedStatement.setString(4, employee.getPassword());
                System.out.println(preparedStatement);
                result = preparedStatement.executeUpdate();
                if(result == 1) System.out.println("User Inserted");
            } catch (SQLException e) {
                JDBCUtils.printSQLException(e);
            }

        return result;
    }
}
