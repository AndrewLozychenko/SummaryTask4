<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<title><fmt:message key="labels.user_list_students"/></title>
<body>
<fmt:message key="labels.user_list_students"/>
<table border="1">
    <tr>
        <th>
            <fmt:message key="labels.name"/>
        </th>
        <th>
            <fmt:message key="labels.surname"/>
        </th>
        <th>
            <fmt:message key="labels.contacts"/>
        </th>
        <th>
            <fmt:message key="labels.login"/>
        </th>
    </tr>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>
                    ${student.name}
            </td>
            <td>
                    ${student.surname}
            </td>
            <td>
                    ${student.contacts}
            </td>
            <td>
                    ${student.login}
            </td>
            <td>
                <c:if test="${student.blocked == false}">
                    <fmt:message key="status.not"/>
                </c:if>
                <fmt:message key="status.blocked"/>
            </td>
            <td>
                <form action="user.block" method="post">
                    <input type="hidden" name="login" value="${student.login}">
                    <c:choose>
                        <c:when test="${student.blocked == true}">
                            <input type="submit" value="<fmt:message key="buttons.unblock"/>">
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="<fmt:message key="buttons.block"/>">
                        </c:otherwise>
                    </c:choose>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<hr>
${message}
</body>
</html>