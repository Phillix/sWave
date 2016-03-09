<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>Internal Error</title>
    </head>
    <body>
        <h1>sWave Error</h1>
        <%if (session.getAttribute("msg") != null) {%>
            <p><%=session.getAttribute("msg")%></p>
        <%} else {%>
            <p>An Unknown Error has Occurred, We Are Sorry</p>
        <%}%>
        <a href="index.jsp">Return Home</a>
    </body>
</html>
