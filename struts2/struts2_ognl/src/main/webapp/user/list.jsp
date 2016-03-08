
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title></title>
</head>
<body>
  <table>
    <tr>
      <td>name</td>
      <td>age</td>
      <td>gender</td>
    </tr>
    <c:forEach items="${userList}" var="item">
      <tr>
        <td>${item.name}</td>
        <td>${item.age}</td>
        <td>${item.gender}</td>
      </tr>
    </c:forEach>

    <s:iterator value="userList" var="bean">
      <tr>
        <td><s:property value="name"/></td>
        <td><s:property value="age"/></td>
        <td><s:property value="gender"/></td>
      </tr>
    </s:iterator>
  </table>

</body>
</html>
