<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String currentSkin;
    if (request.getParameter("skin") == null || request.getParameter("skin").isEmpty()) {
        currentSkin = "nova";
    }
    else {
        currentSkin = request.getParameter("skin");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="macgril/css/base.css"/>
        <!-- Custom skin not possible until after login, using 'nova' by default -->
        <link rel="stylesheet" type="text/css" href="macgril/css/skins/<%=currentSkin%>/<%=currentSkin%>.css"/>
        <link rel="stylesheet" type="text/css" href="css/login.css"/>
    </head>
    <body>
        Switch Skin:
        <a href="login.jsp?skin=quantum">Quantum</a>
        <a href="login.jsp?skin=nova">Nova</a>
        <a href="login.jsp?skin=flat">Flat</a>
        <div class="panel">
            <form action="UserActionServlet" method="post">
                <img id="logo" src="images/logo_black.png"/><br/>
                <input type="hidden" name="action" value="login"/>
                <input class="text" required type="text" name="email" placeholder="Email"/><br/>
                <input class="text" required type="password" name="password" placeholder="Password"/><br/><br/>
                <span id="noAccount">Don't Have an Account Yet?&#160;&#160;<a href="register.jsp">Register</a><br/><br/></span>
                <input id="signupButton" type="button" value="Sign Up" onclick="window.location='register.jsp';"/>
                <input id="loginButton"  type="submit" value="Login"/>
                <input id="cancelButton" type="button" value="Cancel" onclick="window.location='index.jsp';"/>
            </form>
        </div>
        <div id="wallpaper"></div>
    </body>
</html>
