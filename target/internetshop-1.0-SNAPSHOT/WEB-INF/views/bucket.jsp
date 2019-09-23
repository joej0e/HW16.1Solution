<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bucket</title>
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
<h3>Welcome to your bucket!</h3>
<table>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="item" items="${bucket.items}">
        <tr>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td><a href="/internetshop_war_exploded/servlet/deleteFromBucket?bucket_id=${bucket.id}&item_id=${item.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td>Amount</td>
        <td>${amount}</td>
        <td><a href="/internetshop_war_exploded/servlet/completeOrder?bucket_id=${bucket.id}">Checkout</a></td>
    </tr>
</table>
<br>
<a href="/internetshop_war_exploded/shop">Shop&#8594;</a>
<br>
<a href="/internetshop_war_exploded/servlet/showAllOrders">Orders&#8594;</a>
</body>
</html>