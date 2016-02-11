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
            <h5 style="color:pink;">Invalid Credentials, Please Try Again</h5>
            <input required type="text" name="email"/><br/>
            <input required type="password" name="password"/><br/>
            <h5>Don't Have an Account Yet?&#160;&#160;<a href="register.jsp">Register</a>&#160;&#160;<a href="index.jsp">Cancel</a></h5>
            <input type="submit" value="Login"/>
        </form>
        <img id="wallpaper" src="images/bg.jpg"/>
    </body>
</html>
