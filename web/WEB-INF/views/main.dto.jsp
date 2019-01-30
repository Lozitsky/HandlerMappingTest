<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Надія
  Date: 30.01.2019
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Main Page</title>
</head>
<body>
<p><spring:message code="greeting.hello"/> ${loginDto.name}</p>
<p><spring:message code="info.password"/> ${loginDto.password}</p>
<p><spring:message code="admin.value"/> ${loginDto.admin}</p>
<p><spring:message code="locale.name"/> ${locale} </p>
</body>
</html>
