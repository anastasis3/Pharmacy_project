<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Главная страница</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
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

        form {
            margin: 20px 0;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }

        input[type="text"], input[type="number"], input[type="file"], select {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 15px;
        }

        button {
            background-color: #000;
            color: #fff;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #333;
        }

        .logout-link {
            margin-top: 20px;
            display: inline-block;
            text-decoration: none;
            color: #000;
            font-size: 16px;
        }

        .lang-switch {
            margin-bottom: 20px;
        }

        .lang-switch button {
            background-color: transparent;
            border: 1px solid #000;
            color: #000;
            padding: 5px 10px;
            margin: 0 5px;
            cursor: pointer;
            font-size: 14px;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        .lang-switch button:hover {
            background-color: #000;
            color: #fff;
        }
    </style>
</head>
<body>
<div class="container">
    <fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'en'}" />
    <fmt:setBundle basename="messages" />

    <!-- Переключатель языка -->
    <div class="lang-switch">
        <form method="get" action="controller">
            <input type="hidden" name="command" value="changeLanguage">
            <button type="submit" name="lang" value="en">EN</button>
            <button type="submit" name="lang" value="ru">RU</button>
        </form>
    </div>


    <h1><fmt:message key="header.greeting" />, <%= request.getSession().getAttribute("username") != null ? request.getSession().getAttribute("username") : "Гость" %></h1>
    <h2><fmt:message key="page.title" /></h2>

    <form id="orderForm" action="controller?command=createOrder" method="POST">
        <label for="medicine"><fmt:message key="label.selectMedicine" /></label>
        <select id="medicine" name="medicine" required>
            <option value="1">Парацетамол</option>
            <option value="2">Амоксициллин</option>
        </select>

        <label for="dosage"><fmt:message key="label.dosage" /></label>
        <input type="text" id="dosage" name="dosage" required>

        <label for="quantity"><fmt:message key="label.quantity" /></label>
        <input type="number" id="quantity" name="quantity" min="1" required>

        <label for="prescription"><fmt:message key="label.prescription" /></label>
        <input type="file" id="prescription" name="prescription">

        <button type="submit"><fmt:message key="button.submit" /></button>
    </form>

    <a href="controller?command=logout"><fmt:message key="logout" /></a>
</div>
</body>
</html>
