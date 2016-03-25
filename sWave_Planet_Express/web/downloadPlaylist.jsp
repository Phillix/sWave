<%@page import="sWave.M3U8"%>
<%@page import="Dtos.Playlist"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Download Playlist</title>
    </head>
    <%
        M3U8 playlist = null;
        if (session.getAttribute("playlist") != null) {
            playlist = new M3U8((Playlist)session.getAttribute("playlist"));
        }
    %>
    <body>
        <h4><%=playlist.getFileName()%></h4>
        <textarea>
            <%=playlist.getFileContent()%>
        </textarea>
        <button>Download .m3u8</button>
    </body>
</html>
