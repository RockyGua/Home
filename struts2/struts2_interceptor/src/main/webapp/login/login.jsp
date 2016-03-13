<%@page pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<html>
<body>
    <form action="login.action" method="post">
        <label for="name">用户名</label>
        <input id="name" name="name" type="text"/><br>

        <label for="password">密码</label>
        <input id="password" name="password" type="password"/><br>

        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
