<%-- 
    Document   : download_songs
    Created on : Mar 9, 2016, 11:49:20 AM
    Author     : Brian Millar
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%if (session.getAttribute("bought_songs") != null) {
            for (int i : (ArrayList<Integer>)session.getAttribute("bought_songs")) {%>
                
        <%}
        }%>
    </body>
</html>
