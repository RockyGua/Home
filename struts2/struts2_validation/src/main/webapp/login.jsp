
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
  <title></title>
</head>
<body>
  <s:actionerror/>
  <form action="validate.action" method="post">
    用户名：<input type="text" name="name"/><br>
    密码：  <input type="text" name="password"/><br>
    <input type="submit" value="提交"/>
  </form>
</body>
</html>
