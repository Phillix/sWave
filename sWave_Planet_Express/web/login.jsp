<%-- 
    Document   : login
    Created on : Feb 8, 2016, 10:26:37 PM
    Author     : Brian Millar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="UserActionServlet" method="post">
            <input type="hidden" name="action" value="login"/>
            <input type="text" name="email"/>
            <input type="password" name="password"/>
            <input type="submit"/>
        </form>
        <br/>
        Don't Have an Account Yet?
        <a href="register.jsp">Register</a>
    </body>
</html>
