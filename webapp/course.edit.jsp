<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<title><fmt:message key="labels.edit_course"/></title>
<body>
<jsp:include page="parts/header.jsp"/>
<jsp:include page="parts/profile_menu_${user.type.name}.jsp"/>
<h1><fmt:message key="labels.edit_course"/></h1>
<div>
    <br>
    <form action="course.edit" method="post">
        <table>
            <input type="hidden" name="id" value="${course.id}">
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
                                    <option selected value="${topic.id}">${topic.name}</option>
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
                                <c:when test="${course.teacher.id == teacher.id}">
                                    <option selected value="${teacher.id}">
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
                    <input type="number" name="partyLimit" min="1" value="${course.partyLimit}">
                </td>
            </tr>
        </table>
        <input type="button" value="<fmt:message key="buttons.cancel"/>"
               onclick=location.href="http://localhost:8080/facultative/course.list?filter=all&teacher=0&topic=0">
        <input type="submit" value="<fmt:message key="buttons.accept"/>">
    </form>
    <br>
    <h2>${message}</h2>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>