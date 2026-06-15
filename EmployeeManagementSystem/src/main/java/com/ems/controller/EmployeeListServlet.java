package com.ems.controller;

import com.ems.dao.EmployeeDAO;
import com.ems.model.Employee;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/EmployeeListServlet")
public class EmployeeListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            res.sendRedirect("jsp/login.jsp");
            return;
        }
        
        try {
            EmployeeDAO dao = new EmployeeDAO();
            
            String sort = req.getParameter("sort");
            String order = req.getParameter("order");

            if (order == null) order = "asc";
            if (sort == null) sort = "emp_id";

            String keyword = req.getParameter("keyword");

            System.out.println("Keyword Received = " + keyword);

            if (keyword != null && !keyword.trim().isEmpty()) {

                System.out.println("SEARCH EXECUTED");

                List<Employee> list = dao.searchEmployees(keyword);

                System.out.println("Records Found = " + list.size());

                req.setAttribute("employeeList", list);


                // 🔥 ADD THIS FIX
                req.setAttribute("currentPage", 1);
                req.setAttribute("totalPages", 1);

                RequestDispatcher rd =
                        req.getRequestDispatcher("/jsp/employeeList.jsp");

                rd.forward(req, res);
                return;
            }

            int recordsPerPage = 5;
            int page = 1;
            String p = req.getParameter("page");
            if (p != null) page = Integer.parseInt(p);
            int offset = (page - 1) * recordsPerPage;

            List<Employee> list = dao.getSortedEmployees(sort, order, offset, recordsPerPage);  // ✅ fixed
            System.out.println("Employee Count = " + list.size());
            int totalRecords = dao.getEmployeeCount();
            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            req.setAttribute("employeeList", list);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);

            RequestDispatcher rd = req.getRequestDispatcher("/jsp/employeeList.jsp");
            rd.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher rd = req.getRequestDispatcher("/jsp/error.jsp");
            rd.forward(req, res);
        }
    }
}
