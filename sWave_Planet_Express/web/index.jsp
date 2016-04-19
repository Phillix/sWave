<%@page import="Daos.Dao"%>
<%@page import="Dtos.Song"%>
<%@page import="Dtos.Ad"%>
<%@page import="Daos.AdDao"%>
<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (session == null) {
        response.sendRedirect("login.jsp?refer=index.jsp");
    }

    User currentUser = (User)session.getAttribute("user");

    String skin = "swave";

    if (currentUser != null) {
        skin = currentUser.getSkin();
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>Welcome to sWave</title>
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
        <script src="macgril/js/datetime.js"></script>
        <script src="macgril/js/windowing.js"></script>
        <script src="macgril/js/audio.js"></script>
        <script src="js/three.min.js"></script>
        <script src="js/sWaveAudioSystem.js"></script>
        <script src="js/ajax_image_loader.js"></script>
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
                <a id="index2Link" class="currentPageLink" href="index.jsp">Music</a>
                <a id="shopLink" href="shop.jsp">Shop</a>
                <a id="accountLink" href="account.jsp">Account</a>
                <a id="aboutLink" href="about.jsp">About</a>
            </nav>
            <div id="header_right">
                <a id="cartLink" style="margin-left: 20px;" href="cart.jsp">View My Cart</a>
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
                        response.sendRedirect("login.jsp?refer=index.jsp");
                %>
                    <!-- In case the redirect fails for any reason provide a link -->
                    <a href="login.jsp">Log In</a>
                <%}%>
            </div>
        </header>
        <aside class="panel" id="left_sidebar">
            <a class="currentPageLink" href="index.jsp"><h2>Now Playing</h2></a>
            <a href="music.jsp"><h2>Library</h2></a>
            <a href="playlists.jsp"><h2>Playlists</h2></a>
            <a href="radioTest.jsp"><h2>Radio Streams</h2></a>
        </aside>
        <div id="midsection">
            <h1 id="songTitle">
                <%if (session.getAttribute("currentSong") != null) {%>
                    <%=((Song)session.getAttribute("currentSong")).getTitle()%>
                    &#160;&#160;-&#160;&#160;
                    <%=((Song)session.getAttribute("currentSong")).getArtist()%>
                <%} else {%>
                    Welcome to sWave<%if (currentUser != null) {%>, <%=currentUser.getFname()%>!<%}
                }%>
            </h1>
            <%if (session.getAttribute("currentSong") != null) {%>
                <img id="testImage"/>
                <script>
                    loadArtwork(<%=((Song)session.getAttribute("currentSong")).getSongId()%>, $("testImage"));
                </script>
            <%}%>
            <select id="visualSelection">
                <option>Artwork</option>
                <option>2D Bars</option>
                <option>3D Bars</option>
                <option>Wave</option>
                <option>Particles</option>
            </select>
            <div id="visualizer">
            </div>
        </div>
        <footer class="panel" id="base">
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

