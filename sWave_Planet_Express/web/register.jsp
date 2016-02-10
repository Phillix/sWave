<%-- 
    Document   : register
    Created on : Feb 8, 2016, 10:26:50 PM
    Author     : Brian Millar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" type="text/css" href="main.css"/>
    </head>
    <body>
        <form action="UserActionServlet" method="post">
            <input type="hidden" name="action" value="register"/>
            <input name="username" type="text" placeholder="Username"/><br/>
            <input name="email" type="text" placeholder="Email"/><br/>
            <input name="fname" type="text" placeholder="First Name"/><br/>
            <input name="lname" type="text" placeholder="Last Name"/><br/>
            <input name="password" type="password" placeholder="Password"/><br/>
            <input name="address1" type="text" placeholder="Address Line 1"/><br/>
            <input name="address2" type="text" placeholder="Address Line 2"/><br/>
            <input name="city" type="text" placeholder="City"/><br/>
            <input name="county" type="text" placeholder="County"/><br/>
            <input name="admin" type="checkbox"/><label> Admin</label><br/>
            <input name="verify" type="password" placeholder="Verification Code"/>
            <input type="submit"/>
        </form>
    </body>
</html>
