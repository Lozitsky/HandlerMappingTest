<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Надія
  Date: 28.01.2019
  Time: 17:16
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

<%-- https://stackoverflow.com/questions/46989072/org-apache-jasper-jasperexception-web-inf-views-home-jsp-line-25-column/48364974 --%>

<%-- https://stackoverflow.com/questions/44198519/internationalization-of-placeholders-in-spring --%>
<%--@elvariable id="loginDto" type="com.kirilo.springMVC.models.LoginDTO"--%>
<form:form method="post" commandName="loginDto" class="box login">
    <fieldset class="boxBody">
        <span style="float: top">
            <a href="?lang=uk">uk</a>
            <a href="?lang=en">en</a>
        </span>
        <form:label path="name"><spring:message code="name.value"/> <form:errors path="name" cssClass="error"/></form:label>
        <spring:message code="name.placeholder" var="namePlaceholder"/>
        <form:input path="name" placeholder="${namePlaceholder}" />

        <form:label path="password"><spring:message code="password.value"/><form:errors path="password" cssClass="error"/></form:label>
        <spring:message code="password.placeholder" var="passwordPlaceholder"/>
        <form:password path="password" placeholder="${passwordPlaceholder}"/>
    </fieldset>
    <footer>
        <form:checkbox path="admin"/>
        <form:label path="admin"><spring:message code="admin.value"/></form:label>

        <input type="submit" class="btnLogin" value=<spring:message code="button.login"/> tabindex="4">
    </footer>
</form:form>

</body>
</html>
