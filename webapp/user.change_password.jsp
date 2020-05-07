<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<title><fmt:message key="labels.change_password"/></title>
<body>
<jsp:include page="parts/header.jsp"/>
<jsp:include page="parts/profile_menu_${user.type.name}.jsp"/>
<h1><fmt:message key="labels.change_password"/></h1>
<div>
    <form action="user.change_password" method="post">
        <fmt:message key="labels.password"/><input type="password" name="password">
        <input type="button" value="<fmt:message key="buttons.cancel"/>"
               onclick=location.href="http://localhost:8080/facultative/user.show.jsp">
        <input type="submit" value="<fmt:message key="buttons.accept"/>">
    </form>
    <h2>${message}</h2>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>