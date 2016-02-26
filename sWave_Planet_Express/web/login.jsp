<%-- 
    Document   : login
    Author     : Brian Millar
--%>

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
        <style>
            @media screen and (max-width: 400px) {
                div.panel {
                    position: fixed;
                    margin:   0px;
                    top:      0px;
                    left:     0px;
                    width:    100%;
                    height:   100%;
                    padding:  0px;
                }
                
                input.text {
                    width:    calc(100% - 32px);
                    margin:   8px;
                }
                
                #signupButton {
                    display: block;
                }
                
                #loginButton {
                    width:  calc(100% - 16px);
                    margin: 8px;
                }
                
                #cancelButton {
                    width:  calc(100% - 16px);
                    margin: 8px;
                }
                
                #noAccount {
                    display: none;
                }
            }
        </style>
    </head>
    <body>
        <div class="panel">
            <form action="UserActionServlet" method="post">
                <img id="logo" src="images/logo_black.png"/><br/>
                <input type="hidden" name="action" value="login"/>
                <input class="text" required type="text" name="email" placeholder="Email"/><br/>
                <input class="text" required type="password" name="password" placeholder="Password"/><br/><br/>
                <span id="noAccount">Don't Have an Account Yet?&#160;&#160;<a href="register.jsp">Register</a><br/><br/></span>
                <input id="signupButton" type="button" value="Sign Up" onclick="window.location='register.jsp';"/>
                <input id="loginButton" type="submit" value="Login"/>
                <input id="cancelButton" type="button" value="Cancel" onclick="window.location='index.jsp';"/>
            </form>
        </div>
        <div id="wallpaper"></div>
    </body>
</html>
