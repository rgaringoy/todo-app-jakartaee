package dev.roy.todoapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    public static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        Cookie[] cookies = req.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    System.out.println("JSESSIONID=" + cookie.getValue());
                }
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
        HttpSession httpSession = req.getSession(false);
        System.out.println("User=" + httpSession.getAttribute("user"));
        httpSession.invalidate();
        resp.sendRedirect(getServletContext().getContextPath());
    }
}
