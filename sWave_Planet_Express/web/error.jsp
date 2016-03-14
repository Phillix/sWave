<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>Internal Error</title>
        <link rel="stylesheet" type="text/css" href="macgril/css/base.css"/>
        <link rel="stylesheet" type="text/css" href="layout/base.css"/>
        <%
            User currentUser = (User)session.getAttribute("user");
            String skin = "flat";
            if (currentUser != null) {
                skin = currentUser.getSkin();
            }

            final boolean DEBUG = sWave.Server.DEBUGGING;
        %>
        <link rel="stylesheet" type="text/css" href="macgril/css/skins/<%=skin%>/<%=skin%>.css"/>
    </head>
    <body>
        <h1>sWave Error</h1>
        <%if (request.getParameter("msg") != null) {%>
            <p><%=request.getParameter("msg")%></p>
        <%} else {%>
            <p>An Unknown Error has Occurred, We Are Sorry</p>
        <%}%>
        <a href="index.jsp">Return Home</a><br/><br/><br/>
        <%if (currentUser != null) {%>
            OR you may want to Open a Ticket:
            <form style="display:block;" id="newTicketForm" action="UserActionServlet" method="POST">
                <input type="hidden" name="action" value="createTicket"/>
                <input type="hidden" name="userId" value="<%=currentUser.getUserId()%>"/>
                <textarea id="ticketIssue" name="issue" placeholder="Use this area to describe your issue"></textarea><br/><br/>
                <input type="submit" value="Open"/>
            </form>
        <%}%>
    </body>
</html>
