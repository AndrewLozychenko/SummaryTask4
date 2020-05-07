<%@ taglib prefix="f" uri="/WEB-INF/profile" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<head>
    <title><fmt:message key="labels.user_show"/></title>
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<jsp:include page="parts/profile_menu_${user.type.name}.jsp"/>
<div>
    <br>
    <fmt:message key="labels.user_show"/>
    <hr>
    <jsp:include page="./settings.jsp"></jsp:include>
    <f:profile user="${user}"/>
    <input type="button" value="<fmt:message key="buttons.edit"/>"
           onclick=location.href="http://localhost:8080/facultative/user.edit.jsp">
    <input type="button" value="<fmt:message key="buttons.change_password"/>"
           onclick=location.href="http://localhost:8080/facultative/user.change_password.jsp">
    <br>
    <form action="user.remove" method="post">
        <input type="submit" value="<fmt:message key="buttons.remove"/>">
    </form>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>