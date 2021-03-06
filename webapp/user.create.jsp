<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<head>
    <title><fmt:message key="labels.new_user"/></title>
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<h1><fmt:message key="labels.new_user"/></h1>
<div>
    <form action="user.create" method="post">
        <table>
            <tr>
                <td>
                    <fmt:message key="labels.login"/>
                </td>
                <td>
                    <input name="login" value=${user.login}>
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
            <tr>
                <td>
                    <fmt:message key="labels.name"/>
                </td>
                <td>
                    <input name="name" value=${user.name}>
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="labels.surname"/>
                </td>
                <td>
                    <input name="surname" value=${user.surname}>
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="labels.contacts"/>
                </td>
                <td>
                    <input name="contacts" value=${user.contacts}>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="button" value="<fmt:message key="buttons.cancel"/>"
                           onclick=history.back()>
                </td>
                <td>
                    <input type="submit" value="<fmt:message key="buttons.accept"/>">
                </td>
            </tr>
        </table>
        <input type="hidden" name="type" value="${param.type}">
        <h2>${message}</h2>
    </form>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>