<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<p><spring:message code="greeting.hello"/> ${loginDto.name}</p>
<p><spring:message code="info.password"/> ${loginDto.password}</p>
<p><spring:message code="admin.value"/> ${loginDto.admin}</p>
<p><spring:message code="locale.name"/> ${locale} </p>


<form:form method="post" modelAttribute="uploadedFile" action="upload-file" enctype="multipart/form-data">

    <%--<form:label path="file">Upload File: <form:errors path="file" cssClass="error"/></form:label>--%>
    Upload File:
    <input name="file" type="file"/>
    <form:errors path="file" cssClass="error"/>
    <br/>

    <br/><br/>

<%--
    File to upload: <input type="file" name="file">
    <br/>
--%>

    <input type="submit" value="Upload"> Press here to upload the files!

</form:form>

</body>
</html>
