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
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="main.css"/>
    </head>
    <body>
        <img id="overlay_logo" src="images/logo_black.png"/>
        <form id="loginForm" action="UserActionServlet" method="post">
            <h1>Login</h1>
            <input type="hidden" name="action" value="login"/>
            <input required type="text" name="email"/><br/>
            <input required type="password" name="password"/><br/>
            Don't Have an Account Yet?
            <a href="register.jsp">Register</a><a href="index.jsp">Cancel</a><br/>
            <input type="submit" value="Login"/>
        </form>
        <img id="wallpaper" src="images/bg.jpg"/>
    </body>
</html>
