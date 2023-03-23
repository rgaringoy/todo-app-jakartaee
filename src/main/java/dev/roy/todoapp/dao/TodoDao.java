package dev.roy.todoapp.dao;

import dev.roy.todoapp.model.Todo;

import java.util.List;

public interface TodoDao {

    Todo selectTodo(long todoId);
    List<Todo> selectAllTodos();
    void insertTodo(Todo todo);
    boolean updateTodo(Todo todo);
    boolean deleteTodo(int id);
}
