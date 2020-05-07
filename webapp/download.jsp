<%@ page import="ua.nure.lozychenko.facultative.db.entity.User" %>
<%@page contentType="text/html pageEncoding='UTF-8'" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
    </head>
    <body>

        <%
            String filename = ((User) request.getSession().getAttribute("user")).getLogin() + ".pdf";
            String filepath = "E:/";
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

            java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filepath + filename);

            int i;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
            fileInputStream.close();
        %>

    </body>
</html>