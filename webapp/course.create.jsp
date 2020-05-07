<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<title><fmt:message key="labels.new_course"/></title>
<body>
<fmt:message key="labels.new_course"/>
<br>
<form action="course.create" method="post">
    <table>
        <tr>
            <td>
                <fmt:message key="labels.name"/>
            </td>
            <td>
                <input name="name" value="${course.name}">
            </td>
        </tr>
        <tr>
            <td>
                <fmt:message key="labels.topic"/>
            </td>
            <td>
                <select name="topic">
                    <c:forEach items="${topics}" var="topic">
                        <c:choose>
                            <c:when test="${course.topic.id == topic.id}">
                                <option value="${topic.id}" selected>${topic.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${topic.id}">${topic.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <fmt:message key="types.teacher"/>
            </td>
            <td>
                <select name="teacher">
                    <c:forEach items="${teachers}" var="teacher">
                        <c:choose>
                            <c:when test="${course.teacher.id==teacher.id}">
                                <option value="${teacher.id}" selected>
                                        ${teacher.name} ${teacher.surname}(${teacher.login})
                                </option>
                            </c:when>
                            <c:otherwise>
                                <option value="${teacher.id}">
                                        ${teacher.name} ${teacher.surname}(${teacher.login})
                                </option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <fmt:message key="labels.begin"/>
            </td>
            <td>
                <input type="date" name="begin" value="${course.begin}">
            </td>
        </tr>
        <tr>
            <td>
                <fmt:message key="labels.end"/>
            </td>
            <td>
                <input type="date" name="end" value="${course.end}">
            </td>
        </tr>
        <tr>
            <td>
                <fmt:message key="labels.party_limit"/>
            </td>
            <td>
                <c:choose>
                    <c:when test="${course==null}">
                        <input type="number" name="partyLimit" min="1" value="0">
                    </c:when>
                    <c:otherwise>
                        <input type="number" name="partyLimit" min="1" value="${course.partyLimit}">
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
    <input type="button" value="<fmt:message key="buttons.cancel"/>"
           onclick=location.href="http://localhost:8080/facultative/course.list?filter=all&teacher=0&topic=0">
    <input type="submit" value="<fmt:message key="buttons.accept"/>">
    <br>
    ${message}
</form>
</body>
</html>