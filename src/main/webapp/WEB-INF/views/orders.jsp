<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Orders</title>
    <style type="text/css">
        table {
            border-spacing: 1em .5em;
            padding: 0 2em 1em 0;
            border: 1px solid orange;
        }

        td {
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>
<body>
<h3>All orders of ${user.name}</h3>
<table>
    <tr>
        <th>Date</th>
        <th>Amount</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.date}</td>
            <td>${order.amount}</td>
        </tr>
    </c:forEach>
</table>
<a href="/internetshop_war_exploded/shop">Shop&#8594;</a>

</body>
</html>

