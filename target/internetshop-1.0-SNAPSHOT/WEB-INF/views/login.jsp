<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>${errorMsg}</div>
<form action="login" method="post">
    <div>
        <p>Fill the form above to create account!</p>
        <hr>

        <table>
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
                    <hr>
                    <button type="submit">LOGIN</button>
                </td>
            </tr>
        </table>
    </div>
    <p>Don't have an account?<a href="a">Sign up!</a></p>
    <a href="users">Users</a><br>
</form>
</body>
</html>
