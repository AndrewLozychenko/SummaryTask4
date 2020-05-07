<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<body>
<jsp:include page="parts/header.jsp"/>
<div>
    <h2>${message}</h2>
    <br>
    <c:forEach items="${actions}" var="action">
        <input type="button" value="${action.name}" onclick=location.href="${action.redirect}">
    </c:forEach>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>