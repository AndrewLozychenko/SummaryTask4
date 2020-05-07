<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<title><fmt:message key="labels.topic_list"/></title>
<body>
<jsp:include page="parts/header.jsp"/>
<jsp:include page="parts/profile_menu_${user.type.name}.jsp"/>
<h1><fmt:message key="labels.topic_list"/></h1>
<div>
    <input type="button" value="<fmt:message key="buttons.add"/>"
           onclick=location.href="http://localhost:8080/facultative/topic.create.jsp">
    <table border="1">
        <tr>
            <th>
                <fmt:message key="labels.name"/>
            </th>
        </tr>
        <c:forEach items="${topics}" var="topic">
            <tr>
                <td>
                        ${topic.name}
                </td>
                <td>
                    <form action="topic.edit">
                        <input type="hidden" name="id" value="${topic.id}">
                        <input type="submit" value="<fmt:message key="buttons.edit"/>">
                    </form>
                </td>
                <td>
                    <form action="topic.remove" method="post">
                        <input type="hidden" name="id" value="${topic.id}">
                        <input type="submit" value="<fmt:message key="buttons.remove"/>">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>