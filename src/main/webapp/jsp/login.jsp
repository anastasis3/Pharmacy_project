<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход</title>
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
            padding: 30px 50px;
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

        input[type="text"], input[type="password"], input[type="hidden"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            transition: border-color 0.3s ease;
        }

        input[type="text"]:hover, input[type="text"]:focus,
        input[type="password"]:hover, input[type="password"]:focus {
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

        .register-link {
            margin-top: 15px;
            display: block;
            color: #333;
            font-size: 12px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Вход</h1>
    <div class="error-message">
        <% if (request.getAttribute("errorLoginPassMessage") != null) { %>
        <%= request.getAttribute("errorLoginPassMessage") %>
        <% } %>
    </div>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="login">
        <div class="form-group">
            <input type="text" id="username" name="username" placeholder="Имя пользователя" required>
        </div>
        <div class="form-group">
            <input type="password" id="password" name="password" placeholder="Пароль" required>
        </div>
        <input type="submit" value="Войти">
    </form>
    <a href="controller?command=register" class="register-link">Нет аккаунта? Зарегистрируйтесь</a>
</div>
</body>
</html>