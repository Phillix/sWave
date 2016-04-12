<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%if (session.getAttribute("user") != null) {
        session.invalidate();
    }%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="macgril/css/animation.css"/>
        <link rel="stylesheet" type="text/css" href="macgril/css/skins/<%=sWave.Server.DEFAULT_SKIN%>/base.css"/>
        <link rel="stylesheet" type="text/css" href="macgril/css/skins/<%=sWave.Server.DEFAULT_SKIN%>/<%=sWave.Server.DEFAULT_SKIN%>.css"/>
        <link rel="stylesheet" type="text/css" href="layout/login.css"/>
        <script type="text/javascript" src="macgril/js/dom.js"></script>
        <script type="text/javascript" src="macgril/js/validation.js"></script>
        <script type="text/javascript" src="js/sWaveScripts.js"></script>
        <script type="text/javascript" src="js/sWaveValidation.js"></script>
    </head>
    <body <%if (request.getParameter("failed") != null) {%>onload="quickShake('loginBox', false)"<%}%>>

        <div class="panel" id="customAlert"></div>

        <div class="panel <%if (request.getParameter("failed") == null) {%>noShake<%}%>" id="loginBox">
            <form id="loginForm" onsubmit="validateLogin(event)" action="UserActionServlet" method="POST">
                <svg id="logo" width="194" height="60" viewBox="0 0 300 100">
                    <mask id="mask" x="0" y="0" width="100" height="100">
                        <rect x="0" y="0" width="100" height="100" fill="#fff"/>
                        <ellipse cx="2.5"  cy="0"   rx="30" ry="51" fill="#000"/>
                        <ellipse cx="2.5"  cy="100" rx="30" ry="51" fill="#000"/>
                        <ellipse cx="97.5" cy="0"   rx="70" ry="51" fill="#000"/>
                        <ellipse cx="97.5" cy="100" rx="70" ry="51" fill="#000"/>
                    </mask>
                    <rect class="iconRectFilled" x="6"   y="0" width="3" height="100" mask="url(#mask)"/>
                    <rect class="iconRectFilled" x="12"  y="0" width="3" height="100" mask="url(#mask)"/>
                    <rect class="iconRectFilled" x="18"  y="0" width="3" height="100" mask="url(#mask)"/>
                    <rect class="iconRectFilled" x="24"  y="0" width="3" height="100" mask="url(#mask)"/>
                    <rect class="iconRectFilled" x="30"  y="0" width="3" height="100" mask="url(#mask)"/>
                    <rect class="iconRectFilled" x="36"  y="0" width="3" height="100" mask="url(#mask)"/>
                    <rect class="iconRectFilled" x="42"  y="0" width="3" height="100" mask="url(#mask)"/>
                    <rect class="iconRectFilled" x="48"  y="0" width="3" height="100" mask="url(#mask)"/>
                    <rect class="iconRectFilled" x="54"  y="0" width="3" height="100" mask="url(#mask)"/>
                    <rect class="iconRectFilled" x="60"  y="0" width="3" height="100" mask="url(#mask)"/>
                    <rect class="iconRectFilled" x="66"  y="0" width="3" height="100" mask="url(#mask)"/>
                    <rect class="iconRectFilled" x="72"  y="0" width="3" height="100" mask="url(#mask)"/>
                    <rect class="iconRectFilled" x="78"  y="0" width="3" height="100" mask="url(#mask)"/>
                    <text class="iconText" x="100" y="68" font-size="60">sWave</text>
                </svg><br/><br/>
                <input type="hidden" name="action" value="login"/>
                <%if (request.getParameter("refer") != null) {%>
                    <input type="hidden" name="refer" value="<%=request.getParameter("refer")%>"/>
                <%}%>
                <input id="emailLoginField" class="text" type="text" name="email" placeholder="Email"/><br/>
                <input id="passwordLoginField" class="text" type="password" name="password" placeholder="Password"/>
                <br/><br/>
                <input class="lButton" id="loginButton" type="submit" value="Login"/>
                <input class="lButton" onclick="event.preventDefault(); $('loginForm').style.display='none'; $('registerForm').style.display='block';" type="button" value="Sign Up"/>
            </form>
            <form id="registerForm" onsubmit="validateRegistration(event)" style="display:none;" action="UserActionServlet" method="POST">
                <input type="hidden" name="action" value="register"/>
                <input id="usernameRegField" name="username" type="text" placeholder="Username"/><br/>
                <input id="emailRegField" name="email" type="text" placeholder="Email"/><br/>
                <input id="passwordRegField" name="password" type="password" placeholder="Password"/><br/>
                <input id="fnameRegField" name="fname" type="text" placeholder="First Name"/><br/>
                <input id="lnameRegField" name="lname" type="text" placeholder="Last Name"/><br/>
                <input class="lButton" type="submit" value="Sign Up"/>
                <input class="lButton" onclick="event.preventDefault(); $('registerForm').style.display='none'; $('loginForm').style.display='block';" type="button" value="Log In"/>
            </form>
        </div>
        <div id="wallpaper"></div>
    </body>
</html>
