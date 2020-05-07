<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<title><fmt:message key="labels.user_list_teachers"/></title>
<body>
<jsp:include page="parts/header.jsp"/>
<jsp:include page="parts/profile_menu_${user.type.name}.jsp"/>
<h1><fmt:message key="labels.user_list_teachers"/></h1>
<div>
    <br>
    <input type="button" value="<fmt:message key="buttons.add"/>"
           onclick=location.href="http://localhost:8080/facultative/user.create.jsp?type=teacher">
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
        <c:forEach items="${teachers}" var="teacher">
            <tr>
                <td>
                        ${teacher.name}
                </td>
                <td>
                        ${teacher.surname}
                </td>
                <td>
                        ${teacher.contacts}
                </td>
                <td>
                        ${teacher.login}
                </td>
                <td>
                    <form action="user.remove" method="post">
                        <input type="hidden" name="target" value="${teacher.login}">
                        <input type="submit" value="<fmt:message key="buttons.remove"/>">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <hr>
    <h2>${message}</h2>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>