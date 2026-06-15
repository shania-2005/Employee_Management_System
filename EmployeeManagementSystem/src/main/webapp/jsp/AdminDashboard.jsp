<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.*, com.ems.model.Employee" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">


<style>

body{
    background:#f1f5f9;
    font-family:'Segoe UI',sans-serif;
}

.navbar{
    background:linear-gradient(to right,#0f172a,#1e293b)!important;
    box-shadow:0 5px 15px rgba(0,0,0,.1);
}

.sidebar{
    background:linear-gradient(180deg,#0f172a,#1e293b);
    min-height:100vh;
}

.sidebar a{
    color:white;
    text-decoration:none;
    padding:15px;
    display:block;
    border-radius:12px;
    margin-bottom:10px;
    transition:.3s;
    font-size:16px;
}

.sidebar a:hover{
    background:#2563eb;
    padding-left:20px;
}

.card{
    border:none;
    border-radius:20px;
    box-shadow:0 10px 25px rgba(0,0,0,.08);
    transition:.3s;
}

.card:hover{
    transform:translateY(-5px);
}

.card-header{
    background:linear-gradient(to right,#2563eb,#3b82f6)!important;
    color:white;
    font-weight:bold;
    border:none;
}

.table{
    border-radius:15px;
    overflow:hidden;
}

.table-dark{
    background:#0f172a !important;
}

.btn-danger{
    border-radius:12px;
}

h1{
    font-weight:bold;
}

</style>


</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">

        <span class="navbar-brand">
            Employee Management System
        </span>

        <a href="../LogoutServlet" class="btn btn-danger">
            Logout
        </a>

    </div>
</nav>

<div class="container-fluid">
<div class="row">

    <!-- Sidebar -->
    <div class="col-md-2 sidebar p-3">

        <h4 class="text-white text-center mb-4">
            Admin Panel
        </h4>
<a href="${pageContext.request.contextPath}/AdminDashboardServlet">
    <i class="fa-solid fa-house"></i>
    Dashboard
</a>

<a href="${pageContext.request.contextPath}/jsp/addEmployee.jsp">
    <i class="fa-solid fa-user-plus"></i>
    Add Employee
</a>

<a href="${pageContext.request.contextPath}/EmployeeListServlet">
    <i class="fa-solid fa-users"></i>
    View Employees
</a>

<a href="${pageContext.request.contextPath}/LogoutServlet">
    <i class="fa-solid fa-right-from-bracket"></i>
    Logout
</a>
    </div>

    <!-- Main Content -->
    <div class="col-md-10 p-4">

      
<h2 class="fw-bold">
    Welcome Back, Admin 👋
</h2>

<p class="text-muted mb-4">
    Here's what's happening with your employees today.
</p>



        <!-- Cards -->
        <div class="row">

            <div class="col-md-4">
                <div class="card text-center p-4">
                    <h5>
						<i class="fa-solid fa-users text-primary"></i>
						Total Employees
						</h5>
                    <h1 class="text-primary">${employeeCount}</h1>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card text-center p-4">
                    <h5>
						<i class="fa-solid fa-building text-success"></i>
						Departments
						</h5>
                    <h1 class="text-success">${departmentCount}</h1>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card text-center p-4">
                    <h5>
<i class="fa-solid fa-indian-rupee-sign text-danger"></i>
Average Salary
</h5>
                    <h1 class="text-danger">₹${averageSalary}</h1>
                </div>
            </div>

        </div>

        <!-- Employee Table -->
        <div class="card mt-5">

            <div class="card-header bg-primary text-white">
                Recent Employees
            </div>

            <div class="card-body">

                <table class="table table-hover">

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
List<Employee> recentList =
    (List<Employee>) request.getAttribute("recentEmployees");

if (recentList == null || recentList.size() == 0) {
%>
    <tr>
        <td colspan="5" class="text-center text-danger">
            No recent employees found
        </td>
    </tr>

<%
} else {
    for (Employee emp : recentList) {
%>

    <tr>
        <td><%= emp.getEmpId() %></td>
        <td><%= emp.getName() %></td>
        <td><%= emp.getDepartment() %></td>
        <td><%= emp.getDesignation() %></td>
        <td><%= emp.getSalary() %></td>
    </tr>

<%
    }
}
%>

</tbody>

                </table>

            </div>

        </div>

    </div>

</div>
</div>

</body>
</html>