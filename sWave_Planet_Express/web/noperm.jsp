<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    User currentUser = (User)session.getAttribute("user");

    String skin = "swave";

    if (currentUser != null) {
        skin = currentUser.getSkin();
    }

    final boolean DEBUG = sWave.Server.DEBUGGING;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>Unauthorized Access - sWave</title>
        <!-- Import base Macgril CSS rules -->
        <link rel="stylesheet" type="text/css" href="macgril/css/base.css"/>
        <!-- Import Macgril's set of CSS animations -->
        <link rel="stylesheet" type="text/css" href="macgril/css/animation.css"/>
        <!-- Import Macgril skin to apply -->
        <link rel="stylesheet" type="text/css" href="macgril/css/skins/<%=skin%>/<%=skin%>.css"/>
        <!-- Import sWave site specific CSS -->
        <link rel="stylesheet" type="text/css" href="layout/base.css"/>
        <!-- Import sWave site specific Macgril skin overrides -->
        <link rel="stylesheet" type="text/css" href="layout/skins/<%=skin%>/base.css"/>
    </head>
    <body>
        <h1>Unauthorized Access</h1>
        <h3>You Do Not Have Permission to View this Page!</h3>
        <span>Only system administrators may use the features of this page.</span><br/>
        <span>If you are an admin please log out and log back into your admin account.</span><br/>
        <a href="index.jsp">Go Home</a>
    </body>
</html>
