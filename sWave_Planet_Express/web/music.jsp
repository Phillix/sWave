<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
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
            User currentUser = null;
            String skin = "swave";

            if (session == null || (User)session.getAttribute("user") == null)
                response.sendRedirect("login.jsp?refer=music.jsp");
            else {
                currentUser = (User)session.getAttribute("user");
                skin = currentUser.getSkin();
            }

            final boolean DEBUG = sWave.Server.DEBUGGING;

            if (request.getParameter("addedToCart") != null && request.getParameter("addedToCart").equals("yes")) {
                %><script>alert("Added to Cart")</script><%
            }
            
            int pageNum = 1;

            if (request.getParameter("pageNum") != null) {
                pageNum = Integer.parseInt(request.getParameter("pageNum"));
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>Library - sWave</title>
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
        <script src="js/three.min.js"></script>
        <script src="js/sWaveAudioSystem.js"></script>
        <script src="js/sWaveScripts.js"></script>
        <script src="js/ajax_image_loader.js"></script>
        <script src="js/ajax_streamer.js"></script>
    </head>
    <body onload="<%if (currentUser != null) {%>loadUserPicture(<%=currentUser.getUserId()%>, $('userPic')); <%}%>resumePlay()">
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
                <a class="currentPageLink" href="playing.jsp">Music</a><a href="shop.jsp">Shop</a><a href="account.jsp">Account</a><a href="about.jsp">About</a>
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
            <a href="playing.jsp">Now Playing</a>
            <a class="currentPageLink" href="music.jsp">Library</a>
            <a href="playlists.jsp">Playlists</a>
            <div id="visualizer"></div>
        </aside>
            <%
                SongDao dao = new SongDao();
                ArrayList<Song> songs = dao.getAllSongs();
                
                if (request.getParameter("sorting") != null) {
                    String sorting = request.getParameter("sorting");
                    if (sorting.equals("natural")) {
                        //Collections.sort(songs, );
                    }
                }
                
                int numPages = (int)Math.ceil(songs.size() / 15.0);
                if (pageNum < 1 || pageNum > numPages) pageNum = 1;
            %>
        <div id="midSectionNoPadding">
            <div id="midUnderlayOmni" class="panel"></div>
            <div id="omniBar" class="panel">
                <span id="pageSwitcher">
                    Page: 
                    <%for (int i = 1; i <= numPages; i++) {%>
                        <a href="music.jsp?pageNum=<%=i%>" class="pageNum <%if (i == pageNum) {%>currentPageNum<%}%>"><%=i%></a>
                    <%}%>
                </span>
            </div>
            <ul id="itemList">
            <%
                Song s;
                NumberFormat f = NumberFormat.getCurrencyInstance();
                for (int i = (pageNum * 15) - 15; (i < pageNum * 15) && i < songs.size(); i++) {
                    s = songs.get(i);
                    %>
                    <li class="panel listing songListing">
                        <img class="artwork" id="artwork<%=s.getSongId()%>" alt="Artwork for <%=s.getAlbum()%>" src="images/MP3.png"/>
                        <script>loadArtwork(<%=s.getSongId()%>, $("artwork<%=s.getSongId()%>"))</script>
                        <%if (s.getTitle() != null && !s.getTitle().isEmpty() && !s.getTitle().equalsIgnoreCase("Title")) {%>
                            <span class="songTitle"><%=s.getTitle()%></span><br/>
                        <%}%>
                        <%if (s.getArtist() != null && !s.getArtist().isEmpty() && !s.getArtist().equalsIgnoreCase("Artist")) {%>
                            <span class="songArtist"><%=s.getArtist()%></span><br/>
                        <%}%>
                        <%if (s.getAlbum() != null && !s.getAlbum().isEmpty() && !s.getAlbum().equalsIgnoreCase("Album")) {%>
                            <span class="songAlbum"><%=s.getAlbum()%></span><br/>
                        <%}%>
                        <%if (s.getYear() != 0) {%>
                            <span class="songYear"><%=s.getYear()%></span>
                        <%}%>
                        <!--
                        <%=f.format(s.getPrice())%>
                        <%=s.getGenre()%>
                        <%if (s.getUploaded() != null && ((System.currentTimeMillis() - s.getUploaded().getTime()) > 60480000)) {%>
                            <span style="color:red;">[NEW]</span>
                        <%}%>
                        <button onclick="$('overlay').style.display='block'; $('songId').value='<%=s.getSongId()%>'; $('addToPlaylist').style.display='block';">P</button>
                        <form style="display:inline; margin-right:10px;" action="UserActionServlet" method="POST">
                            <input type="hidden" name="action" value="addSongToCart"/>
                            <input type="hidden" name="songid" value="<%=s.getSongId()%>"/>
                            <input type="hidden" name="price" value="<%=s.getPrice()%>"/>
                            <input type="submit" value="C"/>
                        </form>
                        <button onclick='stream(<%=s.getSongId()%>);'>&#9658;</button>-->
                    </li>
                <%}%>
            </ul>
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
        <div id="overlay" onclick="this.style.display='none'; $('addToPlaylist').style.display='none';"></div>
        <div id="addToPlaylist" class="panel">
            <form action="UserActionServlet" method="POST">
                <input type="hidden" name="action" value="addSongToPlaylist"/>
                <input id="songId" type="hidden" name="songId"/>
                <label>Playlist: </label> 
                <select name="playlistId">
                    <%
                        PlaylistDao playDao = new PlaylistDao();
                        for (Playlist p : playDao.getUserPlaylists(currentUser.getUserId())) {%>
                            <option>
                                <%=p.getPlaylistId()%>
                            </option>
                        <%}%>
                </select><br/>
                <input type="submit" value="Add"/>
            </form>
        </div>
        <div id="wallpaper"></div>
    </body>
</html>

