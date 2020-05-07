<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<title><fmt:message key="labels.new_topic"/></title>
<body>
<fmt:message key="labels.new_topic"/>
<form action="topic.create" method="post">
    <fmt:message key="labels.name"/> <input name="name" value="${topic.name}">
    <br>
    <input type="button" value="<fmt:message key="buttons.cancel"/>"
           onclick=location.href="http://localhost:8080/facultative/topic.list">
    <input type="submit" value="<fmt:message key="buttons.accept"/>">
</form>
${message}
</body>
</html>