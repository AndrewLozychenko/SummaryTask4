<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<title><fmt:message key="labels.course_list"/></title>
<body>
<fmt:message key="labels.course_list"/>
<c:if test="${user.type.name == \"admin\"}">
    <input type="button" value="<fmt:message key="buttons.add"/>"
           onclick=location.href="http://localhost:8080/facultative/course.create?">
</c:if>
<form action="course.list">
    <fieldset>
        <legend><fmt:message key="labels.settings"/></legend>
        <c:if test="${user.type.name == \"student\"}">
            <fmt:message key="labels.filter"/>
            <select name="filter">
                <c:forEach var="filter" items="${filters}">
                    <c:choose>
                        <c:when test="${currentFilter==filter[0]}">
                            <option value="${filter[0]}" selected>${filter[1]}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${filter[0]}">${filter[1]}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </c:if>
        <br>
        <fmt:message key="labels.topic"/>
        <select name="topic">
            <option value="0">---</option>
            <c:forEach items="${topics}" var="topic">
                <c:choose>
                    <c:when test="${currentTopic==topic.id}">
                        <option value="${topic.id}" selected>${topic.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${topic.id}">${topic.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <br>
        <fmt:message key="types.teacher"/>
        <select name="teacher">
            <option value="0">---</option>
            <c:forEach items="${teachers}" var="teacher">
                <c:choose>
                    <c:when test="${currentTeacher==teacher.id}">
                        <option value="${teacher.id}" selected>${teacher.name} ${teacher.surname}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${teacher.id}">${teacher.name} ${teacher.surname}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <br>
        <fmt:message key="labels.sort_by_name"/>
        <br>
        <input type="radio" id="nameAsc" name="sort" value="nameAsc">
        <label for="nameAsc">A->Z</label>
        <br>
        <input type="radio" id="nameDesc" name="sort" value="nameDesc">
        <label for="nameDesc">Z->A</label>
        <br>
        <fmt:message key="labels.sort_by_duration"/>
        <br>
        <input type="radio" id="durationAsc" name="sort" value="durationAsc">
        <label for="durationAsc">1->100</label>
        <br>
        <input type="radio" id="durationDesc" name="sort" value="durationDesc">
        <label for="durationDesc">100->1</label>
        <br>
        <fmt:message key="labels.sort_by_students_count"/>
        <br>
        <input type="radio" id="countAsc" name="sort" value="countAsc">
        <label for="countAsc">1->100</label>
        <br>
        <input type="radio" id="countDesc" name="sort" value="countDesc">
        <label for="countDesc">100->1</label>
        <br>
        <input type="submit" value="Apply">
    </fieldset>
</form>
<table border="1">
    <tr>
        <th>
            <fmt:message key="labels.name"/>
        </th>
        <th>
            <fmt:message key="labels.topic"/>
        </th>
        <c:if test="${user.type.name != \"teacher\"}">
            <td>
                <fmt:message key="types.teacher"/>
            </td>
        </c:if>
        <th>
            <fmt:message key="labels.begin"/>
        </th>
        <th>
            <fmt:message key="labels.end"/>
        </th>
        <th>
            <fmt:message key="labels.party_limit"/>
        </th>
    </tr>
    <c:forEach items="${courses}" var="course">
        <tr>
            <td>
                    ${course.name}
            </td>
            <td>
                    ${course.topic.name}
            </td>
            <c:if test="${user.type.name != \"teacher\"}">
                <td>
                        ${course.teacher.name} ${course.teacher.surname}
                </td>
            </c:if>
            <td>
                    ${course.begin}
            </td>
            <td>
                    ${course.end}
            </td>
            <td>
                    ${course.partyLimit}
            </td>
            <c:choose>
                <c:when test="${user.type.name == \"admin\"}">
                    <td>
                        <input type="button" value="<fmt:message key="buttons.edit"/>"
                               onclick=location.href="http://localhost:8080/facultative/course.edit?id=${course.id}"
                    </td>
                    <td>
                        <form action="course.remove" method="post">
                            <input type="hidden" name="id" value="${course.id}">
                            <input type="submit" value="<fmt:message key="buttons.remove"/>">
                        </form>
                    </td>
                </c:when>
                <c:when test="${user.type.name == \"teacher\"}">
                    <td>
                        <input type="button" value="<fmt:message key="buttons.journal"/>"
                               onclick=location.href="http://localhost:8080/facultative/journal.list?course=${course.id}">
                    </td>
                </c:when>
                <c:otherwise>
                    <td>
                        <c:choose>
                            <c:when test="${currentFilter == \"all\"}">
                                <form action="course.join" method="post">
                                    <input type="hidden" name="course" value="${course.id}">
                                    <input type="submit" value="<fmt:message key="buttons.join"/>">
                                </form>
                            </c:when>
                            <c:when test="${filter == \"completed\"}">
                            </c:when>
                            <c:otherwise>
                                <form action="course.leave" method="post">
                                    <input type="hidden" name="course" value="${course.id}">
                                    <input type="submit" value="<fmt:message key="buttons.leave"/>">
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
</body>
</html>