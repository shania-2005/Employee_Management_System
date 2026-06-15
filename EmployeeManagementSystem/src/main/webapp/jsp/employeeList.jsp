<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.ems.model.Employee" %>

<%
    String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee List</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
body {
    background: #eef2f7;
}

.page-title {
    font-weight: 700;
    letter-spacing: 0.5px;
}

.card-ui {
    background: #fff;
    border-radius: 14px;
    box-shadow: 0 6px 20px rgba(0,0,0,0.08);
    padding: 20px;
}

.table thead {
    background: linear-gradient(90deg, #1f2937, #111827);
    color: #fff;
}

.table tbody tr:hover {
    background: #f1f6ff;
    transition: 0.2s;
}

.btn-gradient-success {
    background: linear-gradient(45deg, #16a34a, #22c55e);
    border: none;
    color: white;
}

.btn-gradient-danger {
    background: linear-gradient(45deg, #dc2626, #ef4444);
    border: none;
    color: white;
}

.search-box {
    border-radius: 10px;
    padding: 10px;
}

.pagination .page-link {
    border-radius: 8px;
    margin: 0 4px;
}
</style>

</head>

<body>

<div class="container mt-4">

    <!-- HEADER -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2 class="page-title">👨‍💼 Employee Management</h2>

        <div>
            <a href="<%= ctx %>/jsp/addEmployee.jsp" class="btn btn-gradient-success me-2">
                + Add Employee
            </a>

            <a href="<%= ctx %>/LogoutServlet" class="btn btn-gradient-danger">
                Logout
            </a>
        </div>
    </div>

    <!-- SEARCH -->
    <div class="card-ui mb-3">
        <form action="<%= ctx %>/EmployeeListServlet" method="get">
            <div class="input-group">
                <input type="text" name="keyword" class="form-control search-box"
                       placeholder="Search by Name or Department">
                <button class="btn btn-primary">Search</button>
            </div>
        </form>
    </div>

    <!-- TABLE -->
    <div class="card-ui">

        <div class="table-responsive">
        <table class="table align-middle">

            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Department</th>
                    <th>Designation</th>
                    <th>Salary</th>
                    <th>Actions</th>
                </tr>
            </thead>

            <tbody>

<%
List<Employee> list =
    (List<Employee>) request.getAttribute("employeeList");

if(list != null && !list.isEmpty()) {

    for(Employee e : list) {
%>

<tr>
    <td><%= e.getEmpId() %></td>
    <td><%= e.getName() %></td>
    <td><%= e.getDepartment() %></td>
    <td><%= e.getDesignation() %></td>
    <td>₹<%= String.format("%,.0f", e.getSalary()) %></td>

    <td>
        <a href="<%= ctx %>/UpdateEmployeeServlet?id=<%= e.getEmpId() %>"
           class="btn btn-sm btn-primary">
            Edit
        </a>

        <a href="<%= ctx %>/DeleteEmployeeServlet?id=<%= e.getEmpId() %>"
           class="btn btn-sm btn-danger"
           onclick="return confirm('Are you sure?')">
            Delete
        </a>
    </td>
</tr>

<%
    }

} else {
%>

<tr>
    <td colspan="6" class="text-center text-danger">
        No Employees Found
    </td>
</tr>

<%
}
%>

            </tbody>
        </table>
        </div>

    </div>

    <!-- PAGINATION -->
<%
Integer currentPageObj = (Integer) request.getAttribute("currentPage");
Integer totalPagesObj = (Integer) request.getAttribute("totalPages");

int currentPage = (currentPageObj != null) ? currentPageObj : 1;
int totalPages = (totalPagesObj != null) ? totalPagesObj : 1;
%>

    <nav class="mt-3">
        <ul class="pagination justify-content-center">

<%
for(int i = 1; i <= totalPages; i++) {
%>

<li class="page-item <%= (i == currentPage) ? "active" : "" %>">
    <a class="page-link"
       href="<%= ctx %>/EmployeeListServlet?page=<%= i %>">
        <%= i %>
    </a>
</li>

<%
}
%>

        </ul>
    </nav>

</div>

</body>
</html>