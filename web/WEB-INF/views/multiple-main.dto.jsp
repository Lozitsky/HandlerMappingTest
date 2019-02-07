<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Надія
  Date: 05.02.2019
  Time: 2:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload Multiple Files</title>
    <head>
        <style>
            .error {
                color: red;
            }
        </style>
    </head>
</head>
<body>

<p><spring:message code="greeting.hello"/> ${loginDto.name}</p>
<p><spring:message code="info.password"/> ${loginDto.password}</p>
<p><spring:message code="admin.value"/> ${loginDto.admin}</p>
<p><spring:message code="locale.name"/> ${locale} </p>

<form:form method="post" modelAttribute="multiUploadedFile" action="uploadMultipleFile" enctype="multipart/form-data">
    <fieldset>
        <legend>Add a product</legend>

<%--        <c:forEach var="f" varStatus="fi" items="${multiUploadedFile.files}">
            <c:set var="file" value="files[${fi.index}].file"/>
            &lt;%&ndash;Upload file: <form:input type="file" path="${file}" id="${file}"/>&ndash;%&gt;
            Upload file: <input name="${file}" type="file"/>

            &lt;%&ndash;<form:errors path="${file}" cssClass="error"/>&ndash;%&gt;
            <br/>
        </c:forEach>--%>

        <spring:message text="${files[0].file}" var="er"/>
        <spring:message text="${files[1].file}" var="er1"/>
        <spring:message text="${files[2].file}" var="er2"/>

        Upload File1:
        <input name="files[0].file" type="file"/>
        <form:errors path="files[0].file" cssClass="error"/>
        <br/>
        Upload File2:
        <input name="files[1].file" type="file"/>
        <form:errors path="files[1].file" cssClass="error"/>
        <br/>
        Upload File3:
        <input name="files[2].file" type="file"/>
        <form:errors path="files[2].file" cssClass="error"/>
        <br/>

        <br/><br/>

        <input type="submit" value="Upload"> Press here to upload the files!
    </fieldset>
</form:form>

</body>
</html>
