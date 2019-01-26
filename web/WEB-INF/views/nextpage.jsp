<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>next page</title>
</head>
<body>
	<a href="endsession.request">last page and close Spring session</a>
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