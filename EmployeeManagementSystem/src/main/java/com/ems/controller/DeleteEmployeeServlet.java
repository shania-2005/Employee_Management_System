package com.ems.controller;

import com.ems.dao.EmployeeDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {

	  private static final long serialVersionUID = 1L;
	  
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {


	    HttpSession session = req.getSession(false);

	    if (session == null || session.getAttribute("username") == null) {
	        res.sendRedirect("jsp/login.jsp");
	        return;
	    }
	    
        try {
            int id = Integer.parseInt(req.getParameter("id"));

            EmployeeDAO dao = new EmployeeDAO();
            boolean status = dao.deleteEmployee(id);

            if (status) {
                res.sendRedirect("EmployeeListServlet?msg=deleted");
            } else {
                res.sendRedirect("EmployeeListServlet?msg=failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("EmployeeListServlet?msg=error");
        }
    }
}
