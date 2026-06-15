package com.ems.controller;

import com.ems.dao.UserDAO;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;  // ← YEH ADD KARO

@WebServlet("/LoginServlet") 

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.sendRedirect("jsp/login.jsp");
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDAO dao = new UserDAO();
        String role = dao.authenticate(username, password);

        if (role != null) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);

            if (role.equals("ADMIN")) {
            	res.sendRedirect("AdminDashboardServlet");
            } else {
            	res.sendRedirect("EmployeeDashboardServlet");
            }

        } else {
            res.sendRedirect("jsp/error.jsp");
        }

    }
}
