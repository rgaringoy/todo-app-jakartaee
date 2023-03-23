package dev.roy.todoapp.controller;

import dev.roy.todoapp.dao.TodoDao;
import dev.roy.todoapp.dao.TodoDaoImpl;
import dev.roy.todoapp.model.Todo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/")
public class TodoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TodoDao todoDao;

    @Override
    public void init()  {
        todoDao = new TodoDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/list":
                listTodo(req, resp);
                break;
            case "/new":
                newTodo(req, resp);
                break;
            case "/insert":
                insertTodo(req, resp);
                break;
            case "/update":
                updateTodo(req, resp);
                break;
            case "/delete":
                deleteTodo(req, resp);
                break;
            default:
                RequestDispatcher dispatcher = req.getRequestDispatcher("/login/login.jsp");
                dispatcher.forward(req, resp);
                break;
        }

    }

    private void updateTodo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
//        int uid = Integer.parseInt(req.getParameter("uid"));
        String title = req.getParameter("title");
        String description  = req.getParameter("description");
        boolean is_done = Boolean.parseBoolean(req.getParameter("status"));

        Todo todo = new Todo(id, title, description, LocalDate.now(), is_done);
        todoDao.updateTodo(todo);
        resp.sendRedirect("list");
    }

    private void insertTodo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        int uid = Integer.parseInt(req.getParameter("uid"));
        String title = req.getParameter("title");
        String description  = req.getParameter("description");
        boolean is_done = Boolean.parseBoolean(req.getParameter("status"));

        Todo todo = new Todo(title, description, LocalDate.now(), is_done);
        todoDao.insertTodo(todo);
        resp.sendRedirect("list");

    }

    private void newTodo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("todo/todo-form.jsp");
        requestDispatcher.forward(req,resp);
    }

    private void deleteTodo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        todoDao.deleteTodo(id);
        resp.sendRedirect("list");
    }

    private void listTodo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Todo> todoList = todoDao.selectAllTodos();
        req.setAttribute("listTodo", todoList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("todo/todolist.jsp");
        requestDispatcher.forward(req, resp);
    }

}
