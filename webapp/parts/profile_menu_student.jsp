<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<input type="button" value="<fmt:message key="labels.course_list"/>"
       onclick=location.href="http://localhost:8080/facultative/course.list?filter=all&teacher=0&topic=0">
<input type="button" value="<fmt:message key="labels.record_book"/>"
       onclick=location.href="http://localhost:8080/facultative/journal.list?">
<input type="button" value="<fmt:message key="buttons.logout"/>"
       onclick=location.href="http://localhost:8080/facultative/user.logout?">