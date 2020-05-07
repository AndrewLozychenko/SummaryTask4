<%@ taglib prefix="f" uri="/WEB-INF/profile" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<title><fmt:message key="labels.user_show"/></title>
<body>
<jsp:include page="parts/profile_menu_${user.type.name}.jsp"/>
<br>
<fmt:message key="labels.user_show"/>
<hr>
<jsp:include page="./settings.jsp"></jsp:include>
<f:profile user="${user}"/>
<input type="button" value="<fmt:message key="buttons.edit"/>"
       onclick=location.href="http://localhost:8080/facultative/user.edit.jsp">
<input type="button" value="<fmt:message key="buttons.change_password"/>"
       onclick=location.href="http://localhost:8080/facultative/user.change_password.jsp">
<form action="user.remove" method="post">
    <input type="submit" value="<fmt:message key="buttons.remove"/>">
</form>
</body>
</html>