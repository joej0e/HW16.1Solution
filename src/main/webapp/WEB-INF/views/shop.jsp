<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SHOP</title>
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
<h3>Welcome, ${user.name}</h3>

<table>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Add</th>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td><a href="/internetshop_war_exploded/servlet/addToBucket?item_id=${item.id}">&#43;</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/internetshop_war_exploded/servlet/bucket">Bucket&#8594;</a>
</body>
</html>