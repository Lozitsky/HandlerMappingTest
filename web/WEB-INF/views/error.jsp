<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Надія
  Date: 10.02.2019
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>

<h2>Application Error, please contact support.</h2>

<h3>Debug Information:</h3>

Requested URL= ${url}<br><br>

Exception= ${exception.message}<br><br>

<strong>Exception Stack Trace</strong><br>
<c:forEach items="${exception.stackTrace}" var="ex">
    ${ex}
</c:forEach>

</body>
</html>
