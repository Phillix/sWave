<%@page import="java.util.ArrayList"%>
<%@page import="Dtos.Playlist"%>
<%@page import="Daos.PlaylistDao"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="Dtos.Song"%>
<%@page import="Daos.SongDao"%>
<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            User currentUser = null;
            String skin = "swave";

            if (session == null || (User)session.getAttribute("user") == null)
                response.sendRedirect("login.jsp?refer=product.jsp");
            else {
                currentUser = (User)session.getAttribute("user");
                skin = currentUser.getSkin();
            }

            final boolean DEBUG = sWave.Server.DEBUGGING;

            PlaylistDao pdao = new PlaylistDao();
            Playlist p = null;
            if (request.getParameter("playlist") == null || pdao.getPlaylistById(Integer.parseInt(request.getParameter("playlist"))) == null) {
                response.sendRedirect("playlists.jsp");
            } else {
                p = pdao.getPlaylistById(Integer.parseInt(request.getParameter("playlist")));
            }
            
            ArrayList<Song> songs = p.getPlaylistContents();
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title><%if (p != null) {%><%=p.getTitle()%><%} else {%>Playlist<%}%> - sWave</title>
        <!-- Import base Macgril CSS rules -->
        <link rel="stylesheet" type="text/css" href="macgril/css/base.css"/>
        <!-- Import Macgril's set of CSS animations -->
        <link rel="stylesheet" type="text/css" href="macgril/css/animation.css"/>
        <!-- Import Macgril skin to apply -->
        <link rel="stylesheet" type="text/css" href="macgril/css/skins/<%=skin%>/<%=skin%>.css"/>
        <!-- Import sWave site specific CSS -->
        <link rel="stylesheet" type="text/css" href="layout/base.css"/>
        <!-- Import sWave site specific Macgril skin overrides -->
        <link rel="stylesheet" type="text/css" href="layout/skins/<%=skin%>/base.css"/>
        <script src="macgril/js/dom.js"></script>
        <script src="macgril/js/io.js"></script>
        <script src="macgril/js/audio.js"></script>
        <script src="macgril/js/datetime.js"></script>
        <script src="macgril/js/notifications.js"></script>
        <script src="js/three.min.js"></script>
        <script src="js/sWaveAudioSystem.js"></script>
        <script src="js/sWaveScripts.js"></script>
        <script src="js/ajax_image_loader.js"></script>
        <script src="js/ajax_streamer.js"></script>
        <script src="js/cover_flow.js"></script>
    </head>
    <body onkeypress="checkKey(event)" onload="<%if (currentUser != null) {%>loadUserPicture(<%=currentUser.getUserId()%>, $('userPic')); <%}%>resumePlay(); flow(<%=songs.size()%>);">
        <header class="panel" id="topbar">
            <svg onclick="window.location.assign('index.jsp')" id="header_logo" width="194" height="60" viewBox="0 0 300 100">
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
                <!-- Bunching up the anchor tags removes the gaps between them caused by the tabbing and inline-block -->
                <a href="playing.jsp">Music</a><a class="currentPageLink" href="shop.jsp">Shop</a><a href="account.jsp">Account</a><a href="about.jsp">About</a>
            </nav>
            <form id="searchBox" action="UserActionServlet" method="POST">
                <input type="hidden" name="action" value="search"/>
                <input type="search" name="searchterm" placeholder="Search"/>
            </form>
            <svg id="cartIcon" onclick="window.location.assign('cart.jsp')" viewBox="0 0 100 100">
                <circle class="iconCircleFilled" cx="78" cy="24" r="4"/>
                <rect class="iconRectFilled" x="76" y="22" width="4" height="8"/>
                <polygon class="iconPolyFilled" points="15,30 25,70 70,70 80,30"/>
                <rect class="iconRectFilled" x="64" y="65" width="4" height="12"/>
                <rect class="iconRectFilled" x="33" y="75" width="37" height="4"/>
                <circle class="iconCircleFilled" cx="33" cy="78" r="5"/>
                <circle class="iconCircleFilled" cx="67" cy="78" r="5"/>
            </svg>
            <img id="userPic" onclick="showHideUserMenu()" width="50" height="50" src="images/test.png"/>
            <div id="userMenu" class="panel">
                <a id="userNameDisplay" href="account.jsp?view=profile"><%=currentUser.getUsername()%></a>
                <form id="logOutButton" action="UserActionServlet" method="POST">
                    <input type="hidden" name="action" value="logout"/>
                    <input type="submit" value="Log Out"/>
                </form>
            </div>
        </header>
        <aside class="panel" id="left_sidebar">
            <a href="playlists.jsp">Back to Playlists</a>
            <div id="visualizer"></div>
        </aside>
        <div id="midsection">
            <div id="midUnderlay" class="panel"></div>
            <div id="coverFlow">
                <%
                for (int i = 1; i <= songs.size(); i++) {%>
                    <img id="cover<%=i%>"/>
                    <script>
                        loadArtwork(<%=songs.get(i - 1).getSongId()%>, $("cover<%=i%>"));
                    </script>
                <%}%>
            </div>
            <%if (p != null) {
                NumberFormat f = NumberFormat.getCurrencyInstance();%>
                <h2><%=p.getTitle()%></h2>
                <ul>
                    <%for (Song s : p.getPlaylistContents()) {%>
                    <li>
                        <%=s.getArtist()%> : <%=s.getTitle()%>
                        <form action="UserActionServlet" method="POST">
                            <input type="hidden" name="action" value="moveSongInPlaylist"/>
                            <input type="hidden" name="songid" value="<%=s.getSongId()%>"/>
                            <input type="hidden" name="playlistid" value="<%=p.getPlaylistId()%>"/>
                            <input type="hidden" name="direction" value="up"/>
                            <input type="submit" value="Move Up"/>
                        </form>
                        <form action="UserActionServlet" method="POST">
                            <input type="hidden" name="action" value="moveSongInPlaylist"/>
                            <input type="hidden" name="songid" value="<%=s.getSongId()%>"/>
                            <input type="hidden" name="playlistid" value="<%=p.getPlaylistId()%>"/>
                            <input type="hidden" name="direction" value="down"/>
                            <input type="submit" value="Move Down"/>
                        </form>
                        <form action="UserActionServlet" method="POST">
                            <input type="hidden" name="action" value="deleteFromPlaylist"/>
                            <input type="hidden" name="songId" value="<%=s.getSongId()%>"/>
                            <input type="hidden" name="playlistId" value="<%=p.getPlaylistId()%>"/>
                            <input type="submit" value="Remove"/>
                        </form>
                    </li>
                    <%}%>
                </ul>
            <%}%>
        </div>
        <footer class="panel" id="base">
            <svg id="playPauseButton" width="50" height="50" onclick="playPause()" viewBox="20 20 70 60">
                <polygon class="iconPolyFilled" id="playButton" points="33,25 33,75 80,50"/>
                <rect class="iconRectFilled" id="pauseButton1" x="35" y="25" width="10" height="50"/>
                <rect class="iconRectFilled" id="pauseButton2" x="55" y="25" width="10" height="50"/>
            </svg>
            <span id="songInfoDisplay"></span>
            <span id="volControls">
                <svg id="volIcon" viewBox="0 0 100 100">
                    <polygon class="iconPolyFilled" points="75,20 75,80 25,50"/>
                    <rect class="iconRectFilled" x="25" y="40" width="30" height="20"/>
                    <circle class="iconCircleFilled" cx="70" cy="50" r="10"/>
                </svg>
                <input id="volSlider" oninput="updateVol()" type="range" min="0" max="10"/>
            </span>
            <span id="currTimeDisplay">--:--</span>
            <span onclick="jumpTo(event)" id="progressBG"></span>
            <span onclick="jumpTo(event)" id="progress"></span>
            <span id="durationDisplay">--:--</span>
        </footer>
        <div id="notifier" class="panel"></div>
        <div id="wallpaper"></div>
    </body>
</html>

