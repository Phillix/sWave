<%@page import="Dtos.Ad"%>
<%@page import="Daos.AdDao"%>
<%@page import="Dtos.Song"%>
<%@page import="Daos.SongDao"%>
<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%User currentUser = (User)session.getAttribute("user");%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Browse Music</title>
        <link rel="stylesheet" type="text/css" href="macgril/css/base.css"/>
        <link rel="stylesheet" type="text/css" href="main.css"/>
        <%
            String skin = "flat";
            if (currentUser != null) {
                skin = currentUser.getSkin();
            }
            
            final boolean DEBUG = Debugging.Debug.debug;
        %>
        <link rel="stylesheet" type="text/css" href="macgril/css/skins/<%=skin%>/<%=skin%>.css"/>
        <script src="macgril/js/dom.js"></script>
        <script src="macgril/js/io.js"></script>
    </head>
    <body onload="$('player').currentTime = lStore('currentTime')">
        <header class="panel" id="topbar">
            <img id="header_logo" src="images/logo_black.png" height="60"/>
            <nav>
                <a href="index.jsp?filename=<%=request.getParameter("filename")%>&playid=<%=request.getParameter("playid")%>">Now Playing</a>
                <a href="temp.html?filename=<%=request.getParameter("filename")%>&playid=<%=request.getParameter("playid")%>">Library</a>
                <a href="music.jsp?filename=<%=request.getParameter("filename")%>&playid=<%=request.getParameter("playid")%>">Music</a>
                <a href="temp.html?filename=<%=request.getParameter("filename")%>&playid=<%=request.getParameter("playid")%>">Shop</a>
                <a href="account.jsp?filename=<%=request.getParameter("filename")%>&playid=<%=request.getParameter("playid")%>">Account</a>
                <a href="temp.html?filename=<%=request.getParameter("filename")%>&playid=<%=request.getParameter("playid")%>">About</a>
            </nav>
            <div id="header_right">
                <%if (currentUser != null) {%>
                    <a href="account.jsp"><%=currentUser.getUsername()%></a>
                    &#160;&#160;
                    <form style="display:inline;" action="UserActionServlet" method="POST">
                        <input type="hidden" name="action" value="logout"/>
                        <input type="submit" value="Log Out"/>
                    </form>
                <%} else {
                        response.sendRedirect("login.jsp");
                %>
                    <!-- In case the redirect fails for any reason provide a link -->
                    <a href="login.jsp">Log In</a>
                <%}%>
            </div>
        </header>
        <aside class="panel" id="left_sidebar">
            <h2>sWave Streams</h2>
            <h2>Local Tracks</h2>
            <h2>Internet Radio</h2>
        </aside>
        <div id="midsection">
        <table>
        <%
            SongDao dao = new SongDao();
            for (Song s : dao.getAllSongs()) {%>
                <tr>
                    <%if (DEBUG) {%>
                        <td><%=request.getParameter("playid")%></td>
                        <td><%=s.getSongId()%></td>
                    <%}%>
                    <td><%=s.getTitle()%></td>
                    <td><%=s.getArtist()%></td>
                    <td><%=s.getGenre()%></td>
                    <td><%=s.getRelYear()%></td>
                    <td><form action="UserActionServlet" method="POST">
                            <input type="hidden" name="action" value="stream"/>
                            <input type="hidden" name="songid" value="<%=s.getSongId()%>"/>
                            <input type="hidden" name="page" value="music"/>
                            <input type="submit" value="Play"/>
                        </form>
                    </td>
                </tr>
        <%}%>
        </table>
        </div>
        <aside class="panel" id="right_sidebar">
            <%
                AdDao ads = new AdDao();
                Ad ad = ads.getAd((int)Math.floor(Math.random() * ads.getMaxAdId()));
            %>
            <iframe id="ads" src="<%=ad.getAdUrl()%>"></iframe>
        </aside>
        <footer class="panel" id="base">
        </footer>
        <div id="wallpaper"></div>
        <audio id="player" src="http://localhost:8084/<%=request.getParameter("filename")%>" autoplay></audio>
    </body>
</html>

