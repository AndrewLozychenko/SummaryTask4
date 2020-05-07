<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<body>
${message}
<br>
<c:forEach items="${actions}" var="action">
    <input type="button" value="${action.name}" onclick=location.href="${action.redirect}">
</c:forEach>
</body>
</html>