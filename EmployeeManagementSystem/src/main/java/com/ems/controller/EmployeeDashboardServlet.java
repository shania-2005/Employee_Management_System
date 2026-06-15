package com.ems.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ems.dao.EmployeeDAO;
import com.ems.model.Employee;

@WebServlet("/EmployeeDashboardServlet")
public class EmployeeDashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // 🔐 SESSION CHECK
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            res.sendRedirect("jsp/login.jsp");
            return;
        }

        // 👤 logged in user
        String username = (String) session.getAttribute("username");

        // 📊 DAO CALL
        EmployeeDAO dao = new EmployeeDAO();

        // 👉 example: fetch all employees OR user-specific data
        List<Employee> list = dao.getAllEmployees();  // must exist in DAO

        // 📌 set data for JSP
        req.setAttribute("empList", list);
        req.setAttribute("username", username);

        // 👉 forward to JSP
        req.getRequestDispatcher("jsp/employeeDashboard.jsp")
           .forward(req, res);
    }
}