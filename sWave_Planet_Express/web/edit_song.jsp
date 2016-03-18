<%@page import="Dtos.Song"%>
<%@page import="Daos.SongDao"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% if (request.getParameter("songid") != null) {
               SongDao songs = new SongDao();
               Song song = songs.getSongById(Integer.parseInt(request.getParameter("songid")));
            %>
            <form action="UserActionServlet" method="POST">
                <input type="hidden" name="action" value="editSong"/>
                <input type="text" name="title" placeholder="Title" value="<%=song.getTitle()%>"/><br/>
                <input type="text" name="artist" placeholder="Artist" value="<%=song.getArtist()%>"/><br/>
                <input type="text" name="album" placeholder="Album" value="<%=song.getAlbum()%>"/><br/>
                <input type="text" name="genre" placeholder="Genre" value="<%=song.getGenre()%>"/><br/>
                <input type="number" name="year" min="0" max="<%=((new Date()).getYear() + 1900)%>" value="<%=song.getYear()%>"/><br/>
                <textarea name="license"><%=song.getLicense()%></textarea><br/>
                <input type="submit" value="Update"/>
            </form>
        <%}%>
    </body>
</html>
