package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Object userObject = req.getSession().getAttribute("user");
        if (userObject==null){
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        } else{
             req.getRequestDispatcher("/user/hello.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Object passObject = req.getParameter("password");
        Object loginObject = req.getParameter("login");
        if (passObject==null||loginObject==null) {
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        } else {

            String user = loginObject.toString();
            String pass = passObject.toString();

            if (Users.getInstance().getUsers().contains(user) && !(pass.isEmpty())) {
                req.getSession().setAttribute("user",user);
                req.getRequestDispatcher("/user/hello.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }
    }
}
