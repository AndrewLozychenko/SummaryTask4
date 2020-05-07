<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<head>
    <title><fmt:message key="labels.login"/></title>
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<div>
    <form action="user.login">
        <table>
            <tr>
                <td>
                    <fmt:message key="labels.login"/>
                </td>
                <td>
                    <input name="login" value="${login}">
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="labels.password"/>
                </td>
                <td>
                    <input type="password" name="password">
                </td>
            </tr>
        </table>
        <h2>${message}</h2>
        <hr>
        <input type="submit" value="<fmt:message key="buttons.login"/>">
        <hr>
        <input type="button" value="<fmt:message key="buttons.registration"/>"
               onclick=location.href="http://localhost:8080/facultative/user.create.jsp?type=student">
    </form>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>