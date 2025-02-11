<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Подтверждение заказа</title>
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
            color: #333;
        }
        .message {
            margin: 20px 0;
        }
        .button {
            background-color: #000;
            color: #fff;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-decoration: none;
        }
        .button:hover {
            background-color: #333;
        }
        .back-link {
            margin-top: 20px;
            text-decoration: none;
            color: #007bff;
            font-size: 16px;
        }
        .back-link:hover {
            text-decoration: underline;
            color: #333;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Спасибо за ваш заказ!</h1>
    <div class="message">
        <p>Ваш заказ успешно оформлен.</p>
        <p><strong>Детали заказа:</strong></p>
        <ul>
            <li>Препарат: ${medicineName}</li>
            <li>Дозировка: ${dosage}</li>
            <li>Количество: ${quantity}</li>
            <%
                java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = dateFormat.format(new java.util.Date());
            %>
            <li>Дата заказа: <%= formattedDate %></li>
        </ul>
    </div>
    <p>
    <a href="controller?command=viewOrders" class="button">Посмотреть историю заказов</a>
    </p>
    <a href="${pageContext.request.contextPath}/jsp/main.jsp" class="back-link">Назад</a>
</div>
</body>
</html>