<%-- 
    Document   : login
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
            <input required type="text" name="email" placeholder="Email"/><br/>
            <input required type="password" name="password" placeholder="Password"/><br/>
            <h5>Don't Have an Account Yet?&#160;&#160;<a href="register.jsp">Register</a>&#160;&#160;<a href="index.jsp">Cancel</a></h5>
            <input type="submit" value="Login"/>
        </form>
        <img id="wallpaper" src="images/bg.jpg"/>
    </body>
</html>
