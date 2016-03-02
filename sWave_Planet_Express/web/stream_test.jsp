<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Streaming Test</h1>
        <form action="UserActionServlet" method="POST">
            <input type="hidden" name="action" value="stream"/>
            <label>Song Id</label>&#160;<input type="number" name="songid"/>&#160;&#160;
            <input type="submit" value="Start"/>
        </form><br/>
        <audio id="muhStremz" src="http://localhost:8084/<%=request.getParameter("file")%>" autoplay controls></audio>
    </body>
</html>
