package com.ems.controller;

import com.ems.dao.EmployeeDAO;
import com.ems.model.Employee;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UpdateEmployeeServlet")
public class UpdateEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

    	System.out.println("UpdateEmployeeServlet Called");
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            res.sendRedirect("jsp/login.jsp");
            return;
        }
    	
        try {
        	System.out.println("ID = " + req.getParameter("id"));
        	
            int id = Integer.parseInt(req.getParameter("id"));

            EmployeeDAO dao = new EmployeeDAO();
            Employee e = dao.getEmployeeById(id);


System.out.println("Employee Object = " + e);

            req.setAttribute("employee", e);

            RequestDispatcher rd = req.getRequestDispatcher("jsp/updateEmployee.jsp");
            rd.forward(req, res);

        } catch (Exception ex) {
            ex.printStackTrace();
            res.sendRedirect("EmployeeListServlet?msg=error");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {


        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            res.sendRedirect("jsp/login.jsp");
            return;
        }
        
        System.out.println("emp_id = " + req.getParameter("emp_id"));
        System.out.println("id = " + req.getParameter("id"));
        
        try {
            Employee e = new Employee();

            String idStr = req.getParameter("emp_id");

            if (idStr == null || idStr.isEmpty()) {
                res.sendRedirect("EmployeeListServlet?msg=invalid_id");
                return;
            }

            // ✅ FIXED HERE
            e.setEmpId(Integer.parseInt(idStr));

            e.setName(req.getParameter("name"));
            e.setEmail(req.getParameter("email"));
            e.setDepartment(req.getParameter("department"));
            e.setDesignation(req.getParameter("designation"));
            e.setSalary(Double.parseDouble(req.getParameter("salary")));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            e.setJoiningDate(sdf.parse(req.getParameter("joining_date")));

            EmployeeDAO dao = new EmployeeDAO();
            boolean status = dao.updateEmployee(e);

            if (status) {
                res.sendRedirect("EmployeeListServlet?msg=updated");
            } else {
                res.sendRedirect("EmployeeListServlet?msg=failed");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            res.sendRedirect("EmployeeListServlet?msg=error");
        }
    }
}
