<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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

    <script src="http://code.jquery.com/jquery-3.3.1.js"></script>

    <script>
        function doAjax() {
            $.ajax({
                url: 'checkStrength',
                data: {password: $('#password').val()},
                success: function (data) {
                    //first case. We check value of "strengthValue" and change its color
                    /*if (data === "WEAK") {
                        $("#strengthValue").css("color", "red");
                    } else if (data === "AVERAGE") {
                        $("#strengthValue").css("color", "orange");
                    } else {
                        $("#strengthValue").css("color", "green");
                    }*/

                    $("#strengthValue").html(data);
                }
            });
        }
    </script>
</head>
<body>

<%-- https://stackoverflow.com/questions/46989072/org-apache-jasper-jasperexception-web-inf-views-home-jsp-line-25-column/48364974 --%>

<%--@elvariable id="user" type="com.kirilo.springMVC.models.User"--%>
<%-- https://stackoverflow.com/questions/44198519/internationalization-of-placeholders-in-spring --%>
<form:form method="post" commandName="user" action="check-user" class="box login">
    <fieldset class="boxBody">
        <p class="lang">
            <a href="?lang=uk">uk</a>
            <a href="?lang=en">en</a>
        </p>
        <form:label path="name"><spring:message code="name.value"/> <form:errors path="name"
                                                                                 cssClass="error"/></form:label>
        <spring:message code="name.placeholder" var="namePlaceholder"/>
        <form:input path="name" placeholder="${namePlaceholder}" required=""/>

        <form:label path="password"><spring:message code="password.value"/>
            <form:errors path="password" cssClass="error"/>
            <span style="float: right" id="strengthValue"></span>
        </form:label>
        <spring:message code="password.placeholder" var="passwordPlaceholder"/>
        <form:password path="password" placeholder="${passwordPlaceholder}" required="" onkeyup="doAjax()"/>
    </fieldset>
    <footer>
        <form:checkbox path="admin"/>
        <form:label path="admin"><spring:message code="admin.value"/></form:label>

        <input type="submit" class="btnLogin" value=
            <spring:message code="button.login"/> tabindex="4">
    </footer>
</form:form>

</body>
</html>
