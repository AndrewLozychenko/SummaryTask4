<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<body>
<div>
    <input type="button" value="<fmt:message key="labels.user_list_admins"/>"
           onclick=location.href="http://localhost:8080/facultative/user.list?type=admin">
    <input type="button" value="<fmt:message key="labels.user_list_teachers"/>"
           onclick=location.href="http://localhost:8080/facultative/user.list?type=teacher">
    <input type="button" value="<fmt:message key="labels.user_list_students"/>"
           onclick=location.href="http://localhost:8080/facultative/user.list?type=student">
    <input type="button" value="<fmt:message key="labels.topic_list"/>"
           onclick=location.href="http://localhost:8080/facultative/topic.list?">
    <input type="button" value="<fmt:message key="labels.course_list"/>"
           onclick=location.href="http://localhost:8080/facultative/course.list?filter=all&teacher=0&topic=0">
    <input type="button" value="<fmt:message key="buttons.logout"/>"
           onclick=location.href="http://localhost:8080/facultative/user.logout?">
</div>
</body>
</html>
