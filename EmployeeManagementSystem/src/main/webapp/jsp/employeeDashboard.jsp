<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.ems.model.Employee" %>

<!DOCTYPE html>
<html>
<head>
    <title>Employee Dashboard</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body class="bg-light">

<div class="container mt-4">

    <h2 class="mb-3">
        Employee Dashboard
    </h2>

    <!-- 👤 USERNAME -->
    <h4 class="text-primary">
        Welcome ${username} 👤
    </h4>

    <hr>

    <!-- 📊 EMPLOYEE TABLE -->
    <table class="table table-bordered table-hover bg-white">

        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Department</th>
            <th>Designation</th>
            <th>Salary</th>
        </tr>
        </thead>

        <tbody>
<%
@SuppressWarnings("unchecked")
List<Employee> empList =
    (List<Employee>) request.getAttribute("empList");

if(empList != null){
    for(Employee e : empList){
%>

        <tr>
            <td><%= e.getEmpId() %></td>
            <td><%= e.getName() %></td>
            <td><%= e.getDepartment() %></td>
            <td><%= e.getDesignation() %></td>
            <td><%= e.getSalary() %></td>
        </tr>

        <%
                }
            }
        %>

        </tbody>

    </table>

    <!-- 🔓 LOGOUT -->
    <a class="btn btn-danger" href="${pageContext.request.contextPath}/LogoutServlet">
        Logout
    </a>

</div>

</body>
</html>