<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("user") != null) {
    response.sendRedirect("index.jsp");
}%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="macgril/css/base.css"/>
        <!-- Custom skin not possible until after login, using 'flat' by default -->
        <link rel="stylesheet" type="text/css" href="macgril/css/skins/flat/flat.css"/>
        <link rel="stylesheet" type="text/css" href="css/login.css"/>
        <script type="text/javascript" src="macgril/js/dom.js"></script>
    </head>
    <body <%if (request.getParameter("failed") != null) {%>onload="quickShake('loginBox', false)"<%}%>>
        <div class="panel" id="loginBox">
            <form id="loginForm" action="UserActionServlet" method="POST">
                <img id="logo" src="images/logo_black.png"/><br/>
                <input type="hidden" name="action" value="login"/>
                <input class="text" required type="text" name="email" placeholder="Email"/><br/>
                <input class="text" required type="password" name="password" placeholder="Password"/><br/><br/>
                <input id="loginButton"  type="submit" value="Login"/>
                <input onclick="event.preventDefault(); $('loginForm').style.display='none'; $('registerForm').style.display='block';" type="button" value="Sign Up"/>
            </form>
            <form id="registerForm" style="display:none;" action="UserActionServlet" method="POST">
                <input type="hidden" name="action" value="register"/>
                <input required name="username" type="text" placeholder="Username"/><br/>
                <input required name="email" type="text" placeholder="Email" pattern="(.*)(\@)(.*)[.][a-z]{2,3}$"/><br/>
                <input required name="password" type="password" placeholder="Password"/><br/>
                <input pattern="^[A-Z]{1}[a-z]{2,19}$" name="fname" type="text" placeholder="First Name"/><br/>
                <input pattern="^[A-Z]{1}[a-z]{2,19}$" name="lname" type="text" placeholder="Last Name"/><br/>
                <input type="submit" value="Sign Up"/>
                <input onclick="event.preventDefault(); $('registerForm').style.display='none'; $('loginForm').style.display='block';" type="button" value="Log In"/>
            </form>
        </div>
        <div id="wallpaper"></div>
    </body>
</html>
