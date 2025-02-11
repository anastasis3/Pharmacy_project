<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Редактировать заказ</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 20px;
    }

    .container {
      max-width: 600px;
      margin: auto;
      background-color: #fff;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }

    h1 {
      text-align: center;
      color: #333;
      margin-bottom: 20px;
    }

    .error-message {
      color: red;
      margin-bottom: 15px;
    }

    label {
      display: block;
      margin-bottom: 5px;
    }

    input[type="number"], select {
      width: 100%;
      padding: 10px;
      font-size: 16px;
      border: 1px solid #ccc;
      border-radius: 4px;
      margin-bottom: 15px;
      transition: border-color 0.3s ease;
    }

    input[type="number"]:hover, input[type="number"]:focus,
    select:hover, select:focus {
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
      width: 100%;
    }

    input[type="submit"]:hover {
      background-color: #333;
    }

    a {
      display: inline-block;
      margin-top: 15px;
      text-align: center;
      color: #333;
      text-decoration: none;
    }

    a:hover {
      text-decoration: underline;
      background-color: #333;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Редактировать заказ</h1>

  <c:if test="${not empty errorMessage}">
    <div class="error-message">${errorMessage}</div>
  </c:if>

  <form action="${pageContext.request.contextPath}/controller?command=updateOrder" method="post">
    <input type="hidden" name="orderId" value="${order.id}" />

    <label>Препарат:</label>
    <select name="medicine" required>
      <option value="1" ${order.medicineId == 1 ? 'selected' : ''}>Парацетамол</option>
      <option value="2" ${order.medicineId == 2 ? 'selected' : ''}>Амоксициллин</option>
      <!-- Добавьте другие препараты по мере необходимости -->
    </select>

    <label>Количество:</label>
    <input type="number" name="quantity" value="${order.quantity}" required min="1" />

    <label>Дозировка:</label>
    <input type="number" name="dosage" value="${order.dosage}" required min="1" />

    <input type="submit" value="Сохранить изменения" />
  </form>

  <a href="${pageContext.request.contextPath}/jsp/orderHistory.jsp">Назад</a>
</div>
</body>
</html>