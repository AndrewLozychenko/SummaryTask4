<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<title><fmt:message key="labels.journal_list"/></title>
<body>
<fmt:message key="labels.journal_list"/>
<table border="1">
    <tr>
        <th>
            <fmt:message key="labels.course"/>
        </th>
        <th>
            <c:choose>
                <c:when test="${user.type.name == \"teacher\"}">
                    <fmt:message key="types.student"/>
                </c:when>
                <c:otherwise>
                    <fmt:message key="types.teacher"/>
                </c:otherwise>
            </c:choose>
        </th>
        <th>
            <fmt:message key="labels.mark"/>
        </th>
    </tr>
    <c:forEach var="journal" items="${journal}">
        <tr>
            <input type="hidden" name="journal" value="${journal.id}">
            <td>
                    ${journal.student.course.name}
            </td>
            <td>
                <c:choose>
                    <c:when test="${user.type.name == \"teacher\"}">
                        ${journal.student.user.name} ${journal.student.user.surname}
                    </c:when>
                    <c:otherwise>
                        ${journal.student.course.teacher.name} ${journal.student.course.teacher.surname}
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${user.type.name == \"teacher\"}">
                        <form action="journal.save" method="post">
                            <input type="hidden" name="journal" value="${journal.id}">
                            <input type="number" min="0" maxs="100" name="mark" value="${journal.mark}">
                            <input type="submit" value="<fmt:message key="buttons.save"/>">
                        </form>
                    </c:when>
                    <c:otherwise>
                        ${journal.mark}
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>