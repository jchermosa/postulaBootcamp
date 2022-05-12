<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>BootcampsLogin</title>
</head>
<body>
    <div align=" center">
    <h1>User Login Form</h1>
    <form action="login" method="post">
        <table align = "center">
            <tr><td>Correo:</td> <td><input type="text" name = "correo"></td></tr>
            <tr><td>Password:</td><td><input type="password" name="password"></td></tr>

            <tr><td><input type="submit" value="Login"/></td></tr>

        </table>
    </form>    
    </div>

</body>
</html>