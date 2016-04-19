<%@page import="Dtos.Playlist"%>
<%@page import="Daos.PlaylistDao"%>
<%@page import="sWave.ID3V2"%>
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
                response.sendRedirect("login.jsp?refer=music.jsp");
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
        <title>Library - sWave</title>
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
        <script src="js/ajax_image_loader.js"></script>
        <script src="js/ajax_streamer.js"></script>
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
                        response.sendRedirect("login.jsp?refer=music.jsp");
                %>
                    <!-- In case the redirect fails for any reason provide a link -->
                    <a href="login.jsp">Log In</a>
                <%}%>
            </div>
        </header>
        <aside class="panel" id="left_sidebar">
            <a href="playing.jsp"><h2>Now Playing</h2></a>
            <a class="currentPageLink" href="music.jsp"><h2>Library</h2></a>
            <a href="playlists.jsp"><h2>Playlists</h2></a>
            <div id="visualizer"></div>
        </aside>
        <div id="midsection">
        <ul>
        <%
            SongDao dao = new SongDao();
            for (Song s : dao.getAllSongs()) {%>
                <style>
                    td.label {
                        width:50px;
                    }
                </style>
                <li class="panel songListing">
                    <table style="font-size: 14px; width:100%; height:100%; position:relative; top: 0px; left:0px; right:0px;">
                        <tr>
                            <td rowspan="4" class="artwork">
                                <img class="artwork" id="artwork<%=s.getSongId()%>" alt="Artwork for <%=s.getAlbum()%>" src="images/MP3.png"/>
                                <script>loadArtwork(<%=s.getSongId()%>, $("artwork<%=s.getSongId()%>"))</script>
                            </td>
                            <td colspan="6" class="title">
                                <strong>
                                    <%if (DEBUG) {%>
                                        ID: <%=s.getSongId()%>.&#160;
                                    <%}%>
                                    <%if (session.getAttribute("currentSong") != null && ((Song)session.getAttribute("currentSong")).getSongId() == s.getSongId()) {%>
                                        &#160;&#9658;&#160;
                                    <%}%>
                                    <u>
                                        <%=s.getTitle()%>
                                    </u>
                                </strong>
                            </td>
                            <td class="actionButton">
                                <button onclick="$('overlay').style.display='block'; $('addToPlaylist').style.display='block'">P</button>
                                <!--
                                <form style="display:inline; margin-right:10px;" action="UserActionServlet" method="POST">
                                    <input type="hidden" name="action" value="addSongToPlaylist"/>
                                    <input type="hidden" name="songid" value="<%=s.getSongId()%>"/>
                                    <input type="hidden" name="playlistid" value="0"/>
                                    <input type="submit" value="P"/>
                                </form>
                                -->
                            </td>
                        </tr>
                        <tr>
                            <td class="label">
                                <%if (!s.getArtist().equals("artist")) {%>
                                    <strong>Artist:</strong>
                                <%} else {%>
                                    &#160;
                                <%}%>
                            </td>
                            <td class="artist">
                                <%if (!s.getArtist().equals("artist")) {%>
                                    <%=s.getArtist()%>
                                <%} else {%>
                                    &#160;
                                <%}%>
                            </td>
                            <td class="label">
                                <%if (s.getYear() > 0) {%>
                                    <strong>Year:</strong>
                                <%} else {%>
                                    &#160;
                                <%}%>
                            </td>
                            <td>
                                <%if (s.getYear() > 0) {%>
                                    <%=s.getYear()%>
                                <%} else {%>
                                    &#160;
                                <%}%>
                            </td>
                            <td class="label">
                                <strong>Price:</strong>
                            </td>
                            <td>
                                <%NumberFormat f = NumberFormat.getCurrencyInstance();%>
                                <%=f.format(s.getPrice())%>
                            </td>
                            <td class="actionButton">
                                <form style="display:inline; margin-right:10px;" action="UserActionServlet" method="POST">
                                    <input type="hidden" name="action" value="addSongToCart"/>
                                    <input type="hidden" name="songid" value="<%=s.getSongId()%>"/>
                                    <input type="hidden" name="price" value="<%=s.getPrice()%>"/>
                                    <input type="submit" value="C"/>
                                </form>
                            </td>
                        <tr/>
                        <tr>
                            <td class="label">
                                <%if (!s.getAlbum().equals("album")) {%>
                                    <strong>Album:</strong>
                                <%} else {%>
                                    &#160;
                                <%}%>
                            </td>
                            <td class="album">
                                <%if (!s.getAlbum().equals("album")) {%>
                                    <%=s.getAlbum()%>
                                <%} else {%>
                                    &#160;
                                <%}%>
                            </td>
                            <td class="label">
                                <%if (!s.getGenre().equals("genre")) {%>
                                    <strong>Genre:</strong>
                                <%} else {%>
                                    &#160;
                                <%}%>
                            </td>
                            <td>
                                <%if (!s.getGenre().equals("genre")) {%>
                                    <%=s.getGenre()%>
                                <%} else {%>
                                    &#160;
                                <%}%>
                            </td>
                            <td colspan="2">
                                &#160;
                            </td>
                            <td class="actionButton">
                                <button onclick="streamNG(<%=s.getSongId()%>);">&#9658;</button>
                            </td>
                        </tr>
                    </table>
                </li>
            <%}%>
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
        <div id="overlay" onclick="this.style.display='none'; $('addToPlaylist').style.display='none';"></div>
        <style>
            #addToPlaylist {
                position: fixed;
                width:    200px;
                height:   100px;
                left:     calc(50% - 100px);
                top:      calc(50% - 50px);
                background-color: rgba(255, 255, 255, 0.7);
                box-shadow: 0px 0px 4px #000;
                z-index:    20;
                display:    none;
                -moz-animation: expand 0.6s;
            }
            
            #overlay {
                position:         fixed;
                top:              0px;
                left:             0px;
                z-index:          10;
                background-color: rgba(0, 0, 0, 0.7);
                width:            100%;
                height:           100%;
                display:          none;
                -moz-animation: expand 0.4s;
            }
        </style>
        <div id="addToPlaylist">
            <form action="UserActionServlet" method="POST">
                <input type="hidden" name="action" value="addSongToPlaylist"/>
                <label>Playlist: </label> 
                <select>
                    <%
                        PlaylistDao playDao = new PlaylistDao();
                        for (Playlist p : playDao.getUserPlaylists(currentUser.getUserId())) {%>
                            <option>
                                <%=p.getTitle()%>
                            </option>
                        <%}%>
                </select><br/>
                <input type="submit" value="Add"/>
            </form>
        </div>
        <div id="wallpaper"></div>
        <audio id="player"></audio>
    </body>
</html>

