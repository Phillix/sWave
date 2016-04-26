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
            User currentUser = null;
            String skin = "swave";

            if (session == null || (User)session.getAttribute("user") == null)
                response.sendRedirect("login.jsp?refer=playlists.jsp");
            else {
                currentUser = (User)session.getAttribute("user");
                skin = currentUser.getSkin();
            }

            final boolean DEBUG = sWave.Server.DEBUGGING;

            if (request.getParameter("addedToCart") != null && request.getParameter("addedToCart").equals("yes")) {
                %><script>alert("Added to Cart");</script><%
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>Playlists - sWave</title>
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
        <script src="js/audio_system.js"></script>
        <script src="js/streamer.js"></script>
        <script src="js/image_loader.js"></script>
    </head>
    <body onload="loadUserPicture(<%=currentUser.getUserId()%>, $('userPic')); resumePlay()">
        <header class="panel" id="topbar">
            <%=sWave.Graphics.getLogo()%>
            <nav>
                <!-- Bunching up the anchor tags removes the gaps between them caused by the tabbing and inline-block -->
                <a class="currentPageLink" href="playing.jsp">Music</a><a href="shop.jsp">Shop</a><a href="account.jsp">Account</a><a href="about.jsp">About</a>
            </nav>
            <form id="searchBox" action="UserActionServlet" method="POST">
                <input type="hidden" name="action" value="search"/>
                <input type="search" name="searchterm" placeholder="Search"/>
            </form>
            <%=sWave.Graphics.s_cart%>
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
            <a href="music.jsp">Library</a>
            <a class="currentPageLink" href="playlists.jsp">Playlists</a>
            <div id="visualizer"></div>
        </aside>
        <div id="midSectionNoPadding" class="noPadding">
            <div id="midUnderlayOmni" class="panel"></div>
            <div id="omniBar" class="panel">
                <form id="createPlaylistForm" action="UserActionServlet" method="POST">
                    <input type="hidden" name="action" value="createPlaylist"/>
                    <label>New Playlist: </label>
                    <input required type="text" name="playlistTitle" placeholder="Playlist Title"/>
                    <input type="submit" value="Create"/>
                </form>
            </div>
            <ul id="itemList">
            <%
                PlaylistDao playlists = new PlaylistDao();
                ArrayList<Playlist> playlistz = playlists.getUserPlaylists(currentUser.getUserId());
                ArrayList<Song> contents;
                for (Playlist p : playlistz) {
                    contents = p.getPlaylistContents();
                    %>
                    <li class="panel listing">
                        <h3><%=p.getTitle()%></h3>
                        <span>Songs: <%=contents.size()%></span>
                        <a href="playlist.jsp?playlist=<%=p.getPlaylistId()%>">View Playlist</a>
                        <div style="float:right;">
                            <form action="UserActionServlet" method="POST">
                                <input type="hidden" name="action" value="deletePlaylist"/>
                                <input type="hidden" name="playlistId" value="<%=p.getPlaylistId()%>"/>
                                <input type="submit" value="Delete"/>
                            </form>
                            <button>Play</button>
                        </div>
                    </li>
                <%}
            %>
            </ul>
        </div>
        <%=sWave.UI.footer%>
        <div id="notifier" class="panel"></div>
        <div id="wallpaper"></div>
    </body>
</html>

