<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="registration" method="post">
    <div>
        <p>Fill the form above to create account!</p>
        <hr>

        <table>
            <tr>
                <td>
                    <label for="name"><b>Name</b></label>
                </td>
                <td>
                    <input type="text" placeholder="Enter name" name="name" id="name" required>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="surname"><b>Surname</b></label>
                </td>
                <td>
                    <input type="text" placeholder="Enter surname" name="surname" id="surname" required>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="login"><b>Login</b></label>
                </td>
                <td>
                    <input type="text" placeholder="Enter login" name="login" id="login" required>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="psw"><b>Password</b></label>
                </td>
                <td>
                    <input type="password" placeholder="Enter Password" name="psw" id="psw" required>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="psw-repeat"><b>Repeat Password</b></label>
                </td>
                <td>
                    <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" required>
                </td>
            </tr>
            <tr>
                <td>
                    <hr>
                    <button type="submit">Register</button>
                </td>
            </tr>
        </table>
    </div>
    <a href="/internetshop_war_exploded/servlet/users">Users</a><br>

</form>
</body>
</html>

