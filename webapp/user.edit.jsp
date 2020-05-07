<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<title><fmt:message key="labels.edit_user"/></title>
<body>
<jsp:include page="parts/header.jsp"/>
<jsp:include page="parts/profile_menu_${user.type.name}.jsp"/>
<h1><fmt:message key="labels.edit_user"/></h1>
<div>
    <form action="user.edit" method="post">
        <table>
            <tr>
                <td>
                    <fmt:message key="labels.login"/>
                </td>
                <td>
                    <input name="login" value="${user.login}">
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="labels.name"/>
                </td>
                <td>
                    <input name="name" value="${user.name}">
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="labels.surname"/>
                </td>
                <td>
                    <input name="surname" value="${user.surname}">
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="labels.contacts"/>
                </td>
                <td>
                    <input name="contacts" value="${user.contacts}">
                </td>
            </tr>
        </table>
        <input type="button" value="<fmt:message key="buttons.cancel"/>"
               onclick=location.href="http://localhost:8080/facultative/user.show">
        <input type="submit" value="<fmt:message key="buttons.accept"/>">
    </form>
    <h2>${message}</h2>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>