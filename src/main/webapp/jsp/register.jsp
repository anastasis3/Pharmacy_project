<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
            margin: 0;
        }

        .container {
            text-align: center;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 24px;
            margin-bottom: 20px;
            color: #333;
        }

        .form-group {
            margin-bottom: 15px;
        }

        input[type="text"], input[type="password"], input[type="email"], input[type="hidden"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            transition: border-color 0.3s ease;
        }

        input[type="text"]:hover, input[type="text"]:focus,
        input[type="password"]:hover, input[type="password"]:focus,
        input[type="email"]:hover, input[type="email"]:focus {
            border-color: #333;
        }

        input[type="submit"] {
            background-color: #000;
            color: #fff;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #333;
        }

        .error-message {
            color: red;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Регистрация</h1>
    <form name="registerForm" method="post" action="controller?command=register" onsubmit="return validateForm()">
        <input type="hidden" name="action" value="register" />

        <div class="form-group">
            <input type="text" name="username" placeholder="Введите имя пользователя" required />
        </div>

        <div class="form-group">
            <input type="password" name="password" placeholder="Введите пароль" required />
        </div>

        <div class="form-group">
            <input type="email" name="email" placeholder="Введите email" required />
        </div>

        <div class="form-group">
            <input type="text" name="phone" placeholder="Введите номер телефона" required />
        </div>

        <div class="error-message">
            <%= request.getParameter("error") != null ? request.getParameter("error") : "" %>
        </div>

        <input type="submit" value="Зарегистрироваться" />
    </form>
</div>
<script>
    function validateForm() {
        const username = document.forms["registerForm"]["username"].value;
        const password = document.forms["registerForm"]["password"].value;
        const email = document.forms["registerForm"]["email"].value;
        const phone = document.forms["registerForm"]["phone"].value;

        if (username === "" || password === "" || email === "" || phone === "") {
            alert("Пожалуйста, заполните все поля.");
            return false;
        }
        return true;
    }
</script>
</body>
</html>