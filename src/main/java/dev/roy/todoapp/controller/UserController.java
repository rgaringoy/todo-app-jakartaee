package dev.roy.todoapp.controller;

import dev.roy.todoapp.dao.UserDao;
import dev.roy.todoapp.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    @Override
    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        register(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("register/register.jsp");
    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User employee = new User(firstName,lastName,username,password);

        try {
            boolean checkDuplicate = userDao.checkDuplicateUsername(username);
            if (!checkDuplicate) {
                int result = userDao.registerEmployee(employee);
                if (result == 1) {
                    req.setAttribute("NOTIFICATION", "User Registered Successfully!");
                }
            } else {
                req.setAttribute("NOTIFICATION", "Username has already been taken");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("register/register.jsp");
        dispatcher.forward(req,resp);
    }
}
