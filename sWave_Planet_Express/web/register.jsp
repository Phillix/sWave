<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" type="text/css" href="main.css"/>
    </head>
    <body>
        <img id="overlay_logo" src="images/logo_black.png"/>
        <form id="regForm" action="UserActionServlet" method="post">
            <h1>Register</h1>
            <input type="hidden" name="action" value="register"/>
            <input pattern="(.*)(\@)(.*)[.][a-z]{2,3}$" required name="email" type="text" placeholder="Email"/>
            <input required name="password" type="password" placeholder="Password"/><br/>
            <input pattern="^[A-Z]{1}[a-z]{3,19}$" name="fname" type="text" placeholder="First Name"/>
            <input pattern="^[A-Z]{1}[a-z]{3,19}$" name="lname" type="text" placeholder="Last Name"/><br/>
            <input required name="address1" type="text" placeholder="Address Line 1"/>
            <input name="address2" type="text" placeholder="Address Line 2"/><br/>
            <input required name="city" type="text" placeholder="City"/>
            <input pattern="^[A-Z]{1}[a-z]{3,19}$" required name="county" type="text" placeholder="County"/><br/>
            <input required name="username" type="text" placeholder="Username"/>
            <input type="submit" value="Register"/><a style="margin-left:15px;" href="index.jsp">Cancel</a>
        </form>
        <img id="wallpaper" src="images/bg.jpg"/>
    </body>
</html>
