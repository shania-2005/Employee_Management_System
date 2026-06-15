package com.ems.controller;

import java.io.IOException;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ems.model.Employee;
import com.ems.dao.EmployeeDAO;

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
            return;
        }

        EmployeeDAO dao = new EmployeeDAO();

        // ✅ CALL ONCE ONLY
        List<Employee> recentEmployees = dao.getRecentEmployees();

        int employeeCount = dao.getEmployeeCount();
        int departmentCount = dao.getDepartmentCount();
        double avgSalary = dao.getAverageSalary();

        // ✅ SET ATTRIBUTES (CONSISTENT NAMES)
        request.setAttribute("recentEmployees", recentEmployees);
        request.setAttribute("employeeCount", employeeCount);
        request.setAttribute("departmentCount", departmentCount);
        request.setAttribute("averageSalary", String.format("%.2f", avgSalary));

        // Debug
        System.out.println("Employee Count: " + employeeCount);
        System.out.println("Department Count: " + departmentCount);
        System.out.println("Avg Salary: " + avgSalary);

        RequestDispatcher rd =
                request.getRequestDispatcher("/jsp/AdminDashboard.jsp");
        rd.forward(request, response);
    }
}