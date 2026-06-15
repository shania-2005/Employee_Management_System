package com.ems.controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/EmployeeProfileServlet2")
public class EmployeeProfileServlet2 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        
        if (session == null || session.getAttribute("username") == null) {
            res.sendRedirect("jsp/login.jsp");
            return;
        }
        String username = (String) session.getAttribute("username");

        req.setAttribute("username", username);

        RequestDispatcher rd = req.getRequestDispatcher("/jsp/profile.jsp");
        rd.forward(req, res);
    }
}
