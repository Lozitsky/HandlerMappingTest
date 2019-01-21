<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Надія
  Date: 15.01.2019
  Time: 1:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/resources/css/home.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/reset.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/error.css"/>" rel="stylesheet">
    <title>Login Page</title>
</head>
<body>

<%--https://stackoverflow.com/questions/46989072/org-apache-jasper-jasperexception-web-inf-views-home-jsp-line-25-column/48364974--%>

<%--@elvariable id="user" type="com.kirilo.springMVC.models.User"--%>
<form:form method="post" commandName="user" class="box login">
    <fieldset class="boxBody">
        <form:label path="name">Username: <form:errors path="name" cssClass="error"/></form:label>
        <form:input path="name" placeholder="your name" required=""/>

        <form:label path="password">Password: <form:errors path="password" cssClass="error"/></form:label>
        <form:password path="password" placeholder="your password" required=""/>
    </fieldset>
    <footer>
        <form:checkbox path="admin"/>
        <form:label path="admin">Admin</form:label>

        <input type="submit" class="btnLogin" value="Login" tabindex="4">
    </footer>
</form:form>

</body>
</html>
