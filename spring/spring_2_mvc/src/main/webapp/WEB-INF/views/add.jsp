
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
  <s:form action="/user" commandName="user" method="POST">
    Name:<s:input path="userName"/><br>
    Password:<s:input path="password"/>
    <s:button type="submit" value="Submit"/>
  </s:form>
</head>
<body>

</body>
</html>
