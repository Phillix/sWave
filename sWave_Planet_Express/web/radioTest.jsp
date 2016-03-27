<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function play(url) {
                document.getElementById("radio").src = url;
                document.getElementById("radio").play();
            }
        </script>
    </head>
    <body>
        <button onclick="play('https://lainchan.org/radio_assets/lain.ogg')">Lainchan Radio</button>
        <audio id="radio"></audio>
    </body>
</html>
