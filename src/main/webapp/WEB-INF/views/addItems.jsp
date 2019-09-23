<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="addItems" method="post">
    <div>
        <p>Fill the form above to add item!</p>
        <hr>
        <table>
            <tr>
                <td>
                    <label for="item_name"><b>Item name</b></label>
                </td>
                <td>
                    <input type="text" placeholder="Enter item name" name="item_name" id="item_name" required>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="price"><b>Item price</b></label>
                </td>
                <td>
                    <input type="number" placeholder="Enter price" name="price" id="price" required>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit">Add item</button>
                </td>
            </tr>
        </table>
    </div>
    <a href="index">Home&#8594;</a>

</form>

</body>
</html>

