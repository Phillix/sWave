<%@page import="Dtos.PlayTrack"%>
<%@page import="Dtos.Playlist"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Daos.PlaylistDao"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="Dtos.Ad"%>
<%@page import="Daos.AdDao"%>
<%@page import="Dtos.Song"%>
<%@page import="Daos.SongDao"%>
<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            if (session == null) {
                response.sendRedirect("login.jsp?refer=playlists.jsp");
            }
            User currentUser = (User)session.getAttribute("user");

            String skin = "swave";
            if (currentUser != null) {
                skin = currentUser.getSkin();
            }

            final boolean DEBUG = sWave.Server.DEBUGGING;

            if (request.getParameter("addedToCart") != null && request.getParameter("addedToCart").equals("yes")) {
                %><script>alert("Added to Cart")</script><%
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>Playlists - sWave</title>
        <link rel="stylesheet" type="text/css" href="macgril/css/animation.css"/>
        <link rel="stylesheet" type="text/css" href="macgril/css/skins/<%=skin%>/<%=skin%>.css"/>
        <link rel="stylesheet" type="text/css" href="layout/skins/<%=skin%>/base.css"/>
        <link rel="stylesheet" type="text/css" href="layout/skins/<%=skin%>/music.css"/>
        <script src="macgril/js/dom.js"></script>
        <script src="macgril/js/io.js"></script>
        <script src="macgril/js/audio.js"></script>
        <script src="macgril/js/datetime.js"></script>
        <script src="js/three.min.js"></script>
        <script src="js/sWaveAudioSystem.js"></script>
    </head>
    <body>
        <header class="panel" id="topbar">
            <svg onclick="window.open('index.jsp')" id="header_logo" width="194" height="60" viewBox="0 0 300 100">
                <mask id="mask" x="0" y="0" width="100" height="100">
                    <rect x="0" y="0" width="100" height="100" fill="#fff"/>
                    <ellipse cx="2.5"  cy="0"   rx="30" ry="51" fill="#000"/>
                    <ellipse cx="2.5"  cy="100" rx="30" ry="51" fill="#000"/>
                    <ellipse cx="97.5" cy="0"   rx="70" ry="51" fill="#000"/>
                    <ellipse cx="97.5" cy="100" rx="70" ry="51" fill="#000"/>
                </mask>
                <rect class="iconRectFilled" x="6"   y="0" width="3" height="100" mask="url(#mask)"/>
                <rect class="iconRectFilled" x="12"  y="0" width="3" height="100" mask="url(#mask)"/>
                <rect class="iconRectFilled" x="18"  y="0" width="3" height="100" mask="url(#mask)"/>
                <rect class="iconRectFilled" x="24"  y="0" width="3" height="100" mask="url(#mask)"/>
                <rect class="iconRectFilled" x="30"  y="0" width="3" height="100" mask="url(#mask)"/>
                <rect class="iconRectFilled" x="36"  y="0" width="3" height="100" mask="url(#mask)"/>
                <rect class="iconRectFilled" x="42"  y="0" width="3" height="100" mask="url(#mask)"/>
                <rect class="iconRectFilled" x="48"  y="0" width="3" height="100" mask="url(#mask)"/>
                <rect class="iconRectFilled" x="54"  y="0" width="3" height="100" mask="url(#mask)"/>
                <rect class="iconRectFilled" x="60"  y="0" width="3" height="100" mask="url(#mask)"/>
                <rect class="iconRectFilled" x="66"  y="0" width="3" height="100" mask="url(#mask)"/>
                <rect class="iconRectFilled" x="72"  y="0" width="3" height="100" mask="url(#mask)"/>
                <rect class="iconRectFilled" x="78"  y="0" width="3" height="100" mask="url(#mask)"/>
                <text class="iconText" x="100" y="68" font-size="60">sWave</text>
            </svg>
            <nav>
                <a class="currentPageLink" href="playing.jsp">Music</a>
                <a href="shop.jsp">Shop</a>
                <a href="account.jsp">Account</a>
                <a href="about.jsp">About</a>
            </nav>
            <div id="header_right">
                <form id="searchBox" action="UserActionServlet" method="POST">
                    <input type="hidden" name="action" value="search"/>
                    <input type="search" name="searchterm" placeholder="Search"/>
                </form>
                <%if (currentUser != null) {%>
                    <a id="userNameLink" href="account.jsp"><%=currentUser.getUsername()%></a>
                    &#160;&#160;
                    <form id="logOutButton" action="UserActionServlet" method="POST">
                        <input type="hidden" name="action" value="logout"/>
                        <input type="submit" value="Log Out"/>
                    </form>
                <%} else {
                        response.sendRedirect("login.jsp?refer=playlists.jsp");
                %>
                    <!-- In case the redirect fails for any reason provide a link -->
                    <a href="login.jsp">Log In</a>
                <%}%>
            </div>
        </header>
        <aside class="panel" id="left_sidebar">
            <a href="playing.jsp"><h2>Now Playing</h2></a>
            <a href="music.jsp"><h2>Library</h2></a>
            <a class="currentPageLink" href="playlists.jsp"><h2>Playlists</h2></a>
            <div id="visualizer"></div>
        </aside>
        <div id="midsection">
            <h1>Playlists</h1>
            <form action="UserActionServlet" method="POST">
                <input type="hidden" name="action" value="createPlaylist"/>
                <input type="text" name="playlistTitle" placeholder="Playlist Title"/>
                <input type="submit" value="Create"/>
            </form>
            <ul>
            <%
                PlaylistDao playlists = new PlaylistDao();
                ArrayList<Playlist> playlistz = playlists.getUserPlaylists(currentUser.getUserId());
                for (Playlist p : playlistz) {%>
                    <li>
                        <h3><%=p.getTitle()%></h3>
                        <button>Play</button><br/>
                        <ol>
                            <%for (Song s : p.getPlaylistContents()) {%>
                                <li>
                                    <%=s.getTitle()%>&#160;|&#160;<%=s.getArtist()%>
                                </li>
                            <%}%>
                        </ol>
                        <form action="UserActionServlet" method="POST">
                            <input type="hidden" name="action" value="deletePlaylist"/>
                            <input type="hidden" name="playlistId" value="<%=p.getPlaylistId()%>"/>
                            <input type="submit" value="Delete"/>
                        </form>
                    </li>
                <%}
            %>
            </ul>
        </div>
        <footer class="panel" id="base">
            <span id="playerStatus">No Data</span>
            <span id="controls">
                <svg width="45" height="45" viewBox="0 0 100 100">
                    <circle class="iconCircleStroked" cx="50" cy="50" r="40"/>
                    <polygon class="iconPolyFilled" points="72.5,35 72.5,65 47.5,50"/>
                    <polygon class="iconPolyFilled" points="47.5,35 47.5,65 22.5,50"/>
                </svg>
                <svg id="playPauseButton" width="50" height="50" onclick="playPause()" viewBox="0 0 100 100">
                    <circle class="iconCircleStroked" cx="50" cy="50" r="40"/>
                    <polygon class="iconPolyFilled" id="playButton" points="33,25 33,75 80,50"/>
                    <rect class="iconRectFilled" id="pauseButton1" x="35" y="25" width="10" height="50"/>
                    <rect class="iconRectFilled" id="pauseButton2" x="55" y="25" width="10" height="50"/>
                </svg>
                <svg width="45" height="45" viewBox="0 0 100 100">
                    <circle class="iconCircleStroked" cx="50" cy="50" r="40"/>
                    <polygon class="iconPolyFilled" points="27.5,35 27.5,65 52.5,50"/>
                    <polygon class="iconPolyFilled" points="52.5,35 52.5,65 77.5,50"/>
                </svg>
            </span>
            <span id="trackTimer">
                --:-- / --:--
            </span>
            <span onclick="jumpTo(event)" onmouseover="showScrubber()" onmouseout="hideScrubber()" id="progressBG"></span>
            <span onclick="jumpTo(event)" onmouseover="showScrubber()" onmouseout="hideScrubber()" id="progress"></span>
            <img src="images/scrubber.png" onmouseover="showScrubber()" onmouseout="hideScrubber()" id="scrubber"/>
        </footer>
        <div id="wallpaper"></div>
        <audio id="player"></audio>
    </body>
</html>

