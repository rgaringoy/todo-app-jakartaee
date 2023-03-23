package dev.roy.todoapp.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCUtils {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/demo";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "1234";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            printSQLException(e);
        }
        return connection;
    }

    public static Date getSQLDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }

    public static LocalDate getUtiLDate(Date sqlDate) {
        return sqlDate.toLocalDate();
    }

    public static void printSQLException(SQLException sqlException) {
        for (Throwable e: sqlException) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("ErrorCode: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = sqlException.getCause();
                    while (t != null) {
                        System.out.println("Cause " + t);
                        t = t.getCause();
                    }
            }
        }
    }
}
