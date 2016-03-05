<%-- 
    Document   : account
    Created on : Mar 5, 2016, 3:03:25 PM
    Author     : Brian Millar
--%>

<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%User currentUser = (User)session.getAttribute("user");
          if (currentUser == null) {
              response.sendRedirect("login.jsp");
          }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
