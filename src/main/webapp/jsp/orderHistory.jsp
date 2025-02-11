<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>История заказов</title>
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
            width: 90%; /* Увеличиваем ширину контейнера */
            max-width: 700px; /* Увеличиваем максимальную ширину */
        }
        h1 {
            color: #333;
            font-size: 1.5em; /* Уменьшаем размер шрифта заголовка */
            margin: 0 0 20px; /* Увеличиваем отступ снизу */
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .logout-link {
            margin-top: 20px;
            display: inline-block;
            text-decoration: none;
            color: #000;
            font-size: 16px;
        }
        .logout-link:hover{
            color: #333;
        }
        .action-button {
            background-color: white;
            border: 1px solid #000;
            color: black;
            padding: 5px 20px;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            width: 110px;
            text-align: center;
            transition: background-color 0.3s, color 0.3s;
            font-size: 12px;
        }
        .action-button:hover {
            background-color: black;
            color: white;
        }

        .delete-button{
            background-color: white;
            border: 1px solid #000;
            color: black;
            font-size: 12px;
            padding: 5px 30px;
            margin-bottom: 15px;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            width: 150px;
            text-align: center;
            transition: background-color 0.3s, color 0.3s;
        }
        .delete-button:hover {
            background-color: black;
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>История заказов</h1>
    <c:if test="${not empty successMessage}">
        <div style="color: green;">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div style="color: red;">${errorMessage}</div>
    </c:if>
    <table>
        <thead>
        <tr>
            <th>Препарат</th>
            <th>Количество</th>
            <th>Заказчик</th>
            <th>Дата заказа</th>
            <c:if test="${sessionScope.role == 'admin'}">
                <th>Действия</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty orders}">
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.medicineName}</td>
                    <td>${order.quantity}</td>
                    <td>${order.userName}</td>
                    <td>${order.orderDate}</td>
                    <c:if test="${sessionScope.role == 'admin'}">
                        <td>
                            <form action="${pageContext.request.contextPath}/controller?command=deleteorder" method="post" onsubmit="return confirm('Вы уверены, что хотите удалить этот заказ?');" style="display:inline;">
                                <input type="hidden" name="orderId" value="${order.id}" />
                                <button type="submit" class="delete-button">Удалить</button>
                            </form>
                            <a class="action-button" href="${pageContext.request.contextPath}/controller?command=updateOrder&orderId=${order.id}">Редактировать</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty orders}">
            <tr>
                <td colspan="5">Нет заказов для отображения.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/jsp/main.jsp" class="logout-link">Назад</a>
    <a href="controller?command=logout" class="logout-link">Выход</a>
</div>
</body>
</html>
