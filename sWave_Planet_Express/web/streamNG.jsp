<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <input type="number" min="-1"/>
        <button onclick="beginStream(getElementById('songid').value)"></button>
        <audio autoplay controls></audio>
    </body>
</html>
