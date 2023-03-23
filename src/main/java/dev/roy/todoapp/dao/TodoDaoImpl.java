package dev.roy.todoapp.dao;

import dev.roy.todoapp.model.Todo;
import dev.roy.todoapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoDaoImpl implements TodoDao{

    private static final String SELECT_TODO_BY_ID = "SELECT * FROM todos WHERE id = ?";
    private static final String SELECT_ALL_TODOS = "SELECT * FROM todos ORDER BY id DESC LIMIT 20";
    private static final String INSERT_TODO = "INSERT INTO todos (title, description, is_done, target_date) " +
            "VALUES " +
            "(?, ?, ?, ?)";
    private static final String UPDATE_TODO = "UPDATE todos SET title = ?, description = ?, is_done = ?, target_date = ? " +
            "WHERE id = ?";
    private static final String DELETE_TODO = "DELETE FROM todos WHERE id = ?";

    @Override
    public Todo selectTodo(long todoId) {
        Todo todo = null;

        try (Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODO_BY_ID);) {
            preparedStatement.setLong(1, todoId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                boolean status = resultSet.getBoolean("is_done");
                LocalDate targetDate = resultSet.getDate("target_date").toLocalDate();
                //int uid = resultSet.getInt("uid");
                todo = new Todo(id, title, description, targetDate, status);
            }
        }
        catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return todo;
    }

    @Override
    public List<Todo> selectAllTodos() {
        List<Todo> todos = new ArrayList<>();

        try (Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TODOS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(preparedStatement);

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                boolean status = resultSet.getBoolean("is_done");
                LocalDate targetDate = resultSet.getDate("target_date").toLocalDate();
                //int uid = resultSet.getInt("uid");
                todos.add(new Todo(id, title, description, targetDate, status));
            }
        }
        catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return todos;
    }

    @Override
    public void insertTodo(Todo todo) {
        try (Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODO)) {
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getDescription());
            preparedStatement.setBoolean(3, todo.isStatus());
            preparedStatement.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
            //preparedStatement.setInt(5, todo.getUid());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        }
        catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    @Override
    public boolean updateTodo(Todo todo) {
        boolean updated = false;

        try (Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TODO)) {
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getDescription());
            preparedStatement.setBoolean(3, todo.isStatus());
            preparedStatement.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
            preparedStatement.setLong(5, todo.getId());
            updated = preparedStatement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return updated;
    }

    @Override
    public boolean deleteTodo(int id) {
        boolean deleted = false;

        try (Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TODO)) {
            preparedStatement.setLong(1, id);
            deleted = preparedStatement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return deleted;
    }

}
