<%--
  Created by IntelliJ IDEA.
  User: Надія
  Date: 16.01.2019
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hello ${title}</title>
</head>
<body>
<p>Your message is: ${msg}</p>

<a href="dosomething.request">do something</a>
<hr />
<h3>Request Scope (key==values)</h3>
<%
    java.util.Enumeration<String> reqEnum = request.getAttributeNames();
    while (reqEnum.hasMoreElements()) {
        String s = reqEnum.nextElement();
        out.print(s);
        out.println("==" + request.getAttribute(s));
%><br />
<%
    }
%>
<h3>Session Scope (key==values)</h3>
<%
    java.util.Enumeration<String> sessEnum = request.getSession()
            .getAttributeNames();
    while (sessEnum.hasMoreElements()) {
        String s = sessEnum.nextElement();
        out.print(s);
        out.println("==" + request.getSession().getAttribute(s));
%><br />
<%
    }
%>
</body>
</html>
