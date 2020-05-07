<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<title><fmt:message key="labels.user_list_admins"/></title>
<body>
<jsp:include page="parts/header.jsp"/>
<jsp:include page="parts/profile_menu_${user.type.name}.jsp"/>
<h1><fmt:message key="labels.user_list_admins"/></h1>
<div>
    <input type="button" value="<fmt:message key="buttons.add"/>"
           onclick=location.href="http://localhost:8080/facultative/user.create.jsp?type=admin">
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
        <c:forEach items="${admins}" var="admin">
            <tr>
                <td>
                        ${admin.name}
                </td>
                <td>
                        ${admin.surname}
                </td>
                <td>
                        ${admin.contacts}
                </td>
                <td>
                        ${admin.login}
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