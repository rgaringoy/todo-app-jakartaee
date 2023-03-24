package dev.roy.todoapp.controller;

import dev.roy.todoapp.dao.LoginDao;
import dev.roy.todoapp.model.Login;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;


    public void init() {
        loginDao = new LoginDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authenticate(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login/login.jsp");
    }

    private void authenticate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Login login = new Login(username, password);

            if (loginDao.validate(login)) {
                HttpSession session = req.getSession();
                session.setAttribute("user",login.getUsername());
                session.setMaxInactiveInterval(30*60);
                Cookie cookie = new Cookie("user", login.getUsername());
                resp.addCookie(cookie);
                resp.sendRedirect("list");
            }
            else {

                req.setAttribute("NOTIFICATION",
                        "<div class=\"alert alert-success center\" role=\"alert\">\n" +
                        "      <p>invalid username or password</p>\n" +
                        "   </div>");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("login/login.jsp");
                requestDispatcher.forward(req, resp);
            }

    }
}
