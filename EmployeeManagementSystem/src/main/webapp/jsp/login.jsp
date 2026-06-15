
<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
<title>Employee Login</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:'Segoe UI',sans-serif;
}

body{
    min-height:100vh;
    background:#f5f7ff;
    display:flex;
    justify-content:center;
    align-items:center;
}

.main-container{
    width:1000px;
    height:600px;
    background:white;
    border-radius:25px;
    overflow:hidden;
    display:flex;
    box-shadow:0 15px 40px rgba(0,0,0,.15);
}

.left-side{
    width:50%;
    background:linear-gradient(135deg,#001f6b,#0066ff);
    color:white;
    padding:50px;
    display:flex;
    flex-direction:column;
    justify-content:center;
    align-items:center;
}

.left-side h1{
    font-size:55px;
    font-weight:bold;
}

.left-side h2{
    margin-top:20px;
    font-weight:bold;
}

.left-side p{
    text-align:center;
    margin-top:15px;
    font-size:18px;
    line-height:30px;
}

.left-side img{
    width:300px;
    margin-top:30px;
}

.right-side{
    width:50%;
    display:flex;
    justify-content:center;
    align-items:center;
}

.login-box{
    width:380px;
}

.login-box h2{
    text-align:center;
    margin-bottom:35px;
    font-weight:bold;
}

.form-control{
    height:50px;
    border-radius:12px;
}

.btn-login{
    width:100%;
    height:50px;
    border:none;
    border-radius:12px;
    background:linear-gradient(to right,#0066ff,#00bfff);
    color:white;
    font-size:18px;
    font-weight:bold;
}

.btn-login:hover{
    opacity:.9;
}

.footer{
    text-align:center;
    margin-top:20px;
    color:gray;
}

</style>

</head>

<body>

<div class="main-container">

    <!-- Left Side -->

    <div class="left-side">

        <h1>EMS</h1>

        <h2>Welcome Back!</h2>

        <p>
            Sign in to continue managing employees
            efficiently and securely.
        </p>

        <img src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png">

    </div>


    <!-- Right Side -->

    <div class="right-side">

        <div class="login-box">

            <h2>Employee Login</h2>

            <form action="../LoginServlet" method="post">

                <div class="mb-3">
                    <label>Username</label>
                    <input type="text"
                           name="username"
                           class="form-control"
                           placeholder="Enter username"
                           required>
                </div>

                <div class="mb-4">
                    <label>Password</label>
                    <input type="password"
                           name="password"
                           class="form-control"
                           placeholder="Enter password"
                           required>
                </div>

                <button type="submit" class="btn-login">
                    Login
                </button>

            </form>

            <div class="footer">
                © 2026 Employee Management System
            </div>

        </div>

    </div>

</div>

</body>
</html>
