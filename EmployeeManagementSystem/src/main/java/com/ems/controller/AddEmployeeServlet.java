package com.ems.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ems.dao.EmployeeDAO;
import com.ems.model.Employee;

@WebServlet("/AddEmployeeServlet") 

public class AddEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

    	HttpSession session = req.getSession(false);

    	if (session == null || session.getAttribute("username") == null) {
    	    res.sendRedirect("jsp/login.jsp");
    	    return;
    	}
    	
    	String name = req.getParameter("name");
    	String email = req.getParameter("email");
    	String department = req.getParameter("department");
    	String designation = req.getParameter("designation");
    	String salaryStr = req.getParameter("salary");
    	String joiningDate = req.getParameter("joining_date");

    	if (name == null || name.trim().isEmpty() ||
    	    email == null || email.trim().isEmpty() ||
    	    department == null || department.trim().isEmpty() ||
    	    designation == null || designation.trim().isEmpty() ||
    	    salaryStr == null || salaryStr.trim().isEmpty() ||
    	    joiningDate == null || joiningDate.trim().isEmpty()) {

    	    res.sendRedirect("addEmployee.jsp?msg=invalid");
    	    return;
    	}
    	Employee e = new Employee();
    	
    	e.setName(name);
    	e.setEmail(email);
    	e.setDepartment(department);
    	e.setDesignation(designation);
    	e.setSalary(Double.parseDouble(salaryStr));
    	
    	double salary;

    	try {
    	    salary = Double.parseDouble(salaryStr);
    	} catch (NumberFormatException ex) {
    	    res.sendRedirect("addEmployee.jsp?msg=invalid_salary");
    	    return;
    	}

    	e.setSalary(salary);
    	
    	try {
    	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	    e.setJoiningDate(sdf.parse(req.getParameter("joining_date")));

    	    EmployeeDAO dao = new EmployeeDAO();
    	    boolean status = dao.addEmployee(e);

    	    if (status) {
    	        res.sendRedirect("EmployeeListServlet");
    	    } else {
    	        res.sendRedirect("addEmployee.jsp?msg=failed");
    	    }

    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	}
    }
}