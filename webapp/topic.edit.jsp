<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<title><fmt:message key="labels.edit_topic"/></title>
<body>
<jsp:include page="parts/header.jsp"/>
<jsp:include page="parts/profile_menu_${user.type.name}.jsp"/>
<h1><fmt:message key="labels.edit_topic"/></h1>
<div>
    <form action="topic.edit" method="post">
        <input type="hidden" name="id" value="${topic.id}">
        <fmt:message key="labels.name"/> <input name="name" value="${topic.name}">
        <br>
        <input type="button" value="<fmt:message key="buttons.cancel"/>"
               onclick=location.href="http://localhost:8080/facultative/topic.list?">
        <input type="submit" value="<fmt:message key="buttons.accept"/>">
    </form>
    <h2>${message}</h2>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>