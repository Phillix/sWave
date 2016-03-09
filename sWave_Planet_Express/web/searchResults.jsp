<%@page import="Dtos.User"%>
<%@page import="Dtos.Merch"%>
<%@page import="Dtos.Song"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ArrayList<Song> songs  = (ArrayList<Song>)session.getAttribute("searchResults");
            ArrayList<Merch> merch = (ArrayList<Merch>)session.getAttribute("searchMerchResults");
        %>
        <h1>Showing Results for <%=session.getAttribute("searchTerm")%></h1>
        <h2>Song Results</h2>
        <table>
        <!-- There was a beautiful stream + lambda setup here but JSPs use Java 1.7 :( -->
        <%for (Song s : songs) {%>
                <tr <%if (session.getAttribute("currentSong") != null && ((Song)session.getAttribute("currentSong")).getSongId() == s.getSongId()) {%>class="playing"<%}%>>
                        <td><%=s.getTitle()%></td>
                        <td><%=s.getArtist()%></td>
                        <td><%=s.getGenre()%></td>
                        <td><%=s.getRelYear()%></td>
                        <td><form action="UserActionServlet" method="POST">
                                <input type="hidden" name="action" value="stream"/>
                                <input type="hidden" name="songid" value="<%=s.getSongId()%>"/>
                                <input type="hidden" name="userid" value="<%=((User)session.getAttribute("user")).getUserId() %>"/>
                                <input type="hidden" name="page" value="music"/>
                                <input type="submit" value="Play"/>
                            </form>
                        </td>
                    </tr>
            <%}%>
        </table>
    </body>
</html>
