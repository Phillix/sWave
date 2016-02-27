<%-- 
    Document   : upload
    Author     : Brian Millar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="UserActionServlet" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="action" value="upload"/>
            <input type="file" name="songBlob"/>
            <input type="submit"/>
        </form>
    </body>
</html>
