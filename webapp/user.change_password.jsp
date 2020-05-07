<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<title><fmt:message key="labels.change_password"/></title>
<body>
<fmt:message key="labels.change_password"/>
<form action="user.change_password" method="post">
    <fmt:message key="labels.password"/><input type="password" name="password">
    <input type="button" value="<fmt:message key="buttons.cancel"/>"
           onclick=location.href="http://localhost:8080/facultative/user.show.jsp">
    <input type="submit" value="<fmt:message key="buttons.accept"/>">
</form>
${message}
</body>
</html>