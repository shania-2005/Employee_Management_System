<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ems.model.Employee" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<%
    Employee e = (Employee) request.getAttribute("employee");
%>

<div class="container mt-4" style="max-width: 600px;">
    <h2 class="mb-4">Update Employee</h2>

		<form action="/EmployeeManagementSystem/UpdateEmployeeServlet" method="post">
        <input type="hidden" name="emp_id" value="<%= e.getEmpId() %>">

        <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" name="name" class="form-control" value="<%= e.getName() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" name="email" class="form-control" value="<%= e.getEmail() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Department</label>
            <input type="text" name="department" class="form-control" value="<%= e.getDepartment() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Designation</label>
            <input type="text" name="designation" class="form-control" value="<%= e.getDesignation() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Salary</label>
            <input type="number" name="salary" class="form-control" value="<%= e.getSalary() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Joining Date</label>
            <input type="date" name="joining_date" class="form-control" value="<%= e.getJoiningDate() %>" required>
        </div>

        <button type="submit" class="btn btn-primary">Update</button>
        <a href="../EmployeeListServlet" class="btn btn-secondary ms-2">Cancel</a>
    </form>
</div>

</body>
</html>