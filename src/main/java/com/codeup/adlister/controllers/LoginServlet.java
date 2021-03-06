package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = DaoFactory.getUsersDao().findByUsername(username);

        if (user == null) {
            stickyForm(username, request);
            request.setAttribute("hasError", true);
            request.setAttribute("error","usernameDoesntExist");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        boolean validAttempt = Password.check(password, user.getPassword());

        if (validAttempt) {
            request.getSession().setAttribute("user", user);
            if(request.getSession().getAttribute("target") == null || request.getSession().getAttribute("target").equals("profile")) {
                response.sendRedirect("/profile");
            }else if(request.getSession().getAttribute("target").equals("create")){
                response.sendRedirect("/ads/create");
            }
        } else {
            request.setAttribute("hasError", true);
            request.setAttribute("error","incorrectPasswordError");
            request = stickyForm(username, request);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }


    private HttpServletRequest stickyForm(String username, HttpServletRequest request) {
        request.setAttribute("username", username);
        return request;
    }
}
