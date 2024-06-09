<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Successful</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            text-align: center;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #007bff;
        }
        p {
            color: #6c757d;
        }
        .logo {
            max-width: 100px;
            margin-bottom: 20px;
        }
        .redirect {
            font-size: 18px;
            color: #6c757d;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <img src="src/images/upgrade.svg" alt="Logo" class="logo">
        <h2>Registration Successful</h2>
        <p>Your registration was successful. You will be redirected to the home page shortly.</p>
        <div class="redirect">Redirecting in 10 seconds...</div>
    </div>
    <script>
        setTimeout(function(){
            window.location.href = "home.jsp";
        }, 10000);
    </script>
</body>
</html>

