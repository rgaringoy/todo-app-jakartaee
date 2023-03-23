package dev.roy.todoapp.dao;

import dev.roy.todoapp.model.Login;
import dev.roy.todoapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    private static final String VALIDATE_LOGIN = "SELECT username, password FROM users " +
            "WHERE username = ? AND password = ?";

    public boolean validate(Login login) {
        boolean status = false;

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_LOGIN)) {
            preparedStatement.setString(1, login.getUsername());
            preparedStatement.setString(2, login.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            status = resultSet.next();
            System.out.println(preparedStatement);
            if (status) System.out.println("User has been authenticated");

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;

    }
}
