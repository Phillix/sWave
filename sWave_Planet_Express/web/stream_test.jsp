<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%User currentUser = (User)session.getAttribute("user");
          if (currentUser == null) {
              response.sendRedirect("login.jsp");
          }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="macgril/js/dom.js"></script>
        <script src="macgril/js/io.js"></script>
        <title>JSP Page</title>
    </head>
    <body onload="doit()">
        <h1>Streaming Test</h1>
        <form action="UserActionServlet" method="POST">
            <input type="hidden" name="action" value="stream"/>
            <label>Song Id</label>&#160;<input type="number" id="songid" name="songid"/>&#160;&#160;
            <input type="submit" value="Start"/>
        </form><br/>
        <!-- 
            We add song id parameter to the end of the URL to prevent Firefox 
            from playing the same song over and over as it uses its cached copy 
            if the URL is the same which it always would be as we simply 
            overwrite the same file every time on the server side. While the id 
            is not used it is better than adding randomness as in the case the 
            same song is requested twice the URLs with the id parameter will 
            match and we can make use of the cached copy.
        -->
        <audio id="muhStremz" src="http://localhost:8084/<%=request.getParameter("filename")%>" autoplay controls></audio>
    </body>
</html>
