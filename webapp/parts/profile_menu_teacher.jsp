<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<body>
<div>
    <input type="button" value="<fmt:message key="labels.course_list"/>"
           onclick=location.href="http://localhost:8080/facultative/course.list?">
    <input type="button" value="<fmt:message key="buttons.logout"/>"
           onclick=location.href="http://localhost:8080/facultative/user.logout?">
</div>
</body>
</html>
