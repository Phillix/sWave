<%@page import="Dtos.Song"%>
<%@page import="Dtos.Ad"%>
<%@page import="Daos.AdDao"%>
<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%User currentUser = (User)session.getAttribute("user");%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>Welcome to sWave</title>
        <link rel="stylesheet" type="text/css" href="main.css"/>
        <link rel="stylesheet" type="text/css" href="css/index.css"/>
        <link rel="stylesheet" type="text/css" href="macgril/css/base.css"/>
        <%
            String skin = "flat";
            if (currentUser != null) {
                skin = currentUser.getSkin();
            }
        %>
        <link rel="stylesheet" type="text/css" href="macgril/css/skins/<%=skin%>/<%=skin%>.css"/>
        <script src="macgril/js/dom.js"></script>
        <script src="macgril/js/io.js"></script>
        <script src="macgril/js/datetime.js"></script>
        <script src="macgril/js/windowing.js"></script>
        <script src="macgril/js/audio.js"></script>
        <script src="js/three.min.js"></script>
        <script src="js/sWaveAudioSystem.js"></script>
    </head>
    <body <%if (session.getAttribute("currentSong") != null) {%>onload="initsWaveAudio()"<%}%>>
        <header class="panel" id="topbar">
            <img id="header_logo" src="images/logo_black.png" height="60"/>
            <nav>
                <a class="currentPageLink" href="index.jsp">Music</a>
                <a href="shop.jsp">Shop</a>
                <a href="account.jsp">Account</a>
                <a href="about.jsp">About</a>
            </nav>
            <div id="header_right">
                <form action="UserActionServlet" method="POST">
                    <input type="hidden" name="action" value="search"/>
                    <input type="search" name="searchterm" placeholder="Search"/>
                </form>
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
            <a class="currentPageLink" href="index.jsp">
                <h2>Now Playing</h2>
            </a>
            <a href="music.jsp">
                <h2>Library</h2>
            </a>
            <span id="copyNotice">
                Copyright &copy; 2016<br/>
                Team Planet Express<br/>
            </span>
        </aside>
        <div id="midsection">
            <div id="visualizer">
            </div>
        </div>
        <aside class="panel" id="right_sidebar">
            <%
                AdDao ads = new AdDao();
                int check = (int)Math.ceil(Math.random() * ads.getMaxAdId());
                Ad ad = ads.getAd(check);
            %>
            <iframe id="ads" src="<%=ad.getAdUrl()%>"></iframe>
        </aside>
        <footer class="panel" id="base">
            <span id="playerStatus">No Data</span>
            <span id="controls">
                <!-- Hiding Next and Previous Buttons until Playlist Backend Functionality Compete-->
                <img style="visibility: hidden; width: 30px; height:30px; position:relative; top:-5px;" src="images/rw.png"/>
                <img id="playPauseButton" onclick="playPause()" src="images/play.png"/>
                <img style="visibility: hidden; width: 30px; height:30px; position:relative; top:-5px;" src="images/fw.png"/>
            </span>
            <span id="trackTimer">
                --:-- / --:--
            </span>
            <span onclick="jumpTo(event)" onmouseover="showScrubber()" onmouseout="hideScrubber()" id="progressBG"></span>
            <span onclick="jumpTo(event)" onmouseover="showScrubber()" onmouseout="hideScrubber()" id="progress"></span>
            <img src="images/scrubber.png" onmouseover="showScrubber()" onmouseout="hideScrubber()" id="scrubber"/>
        </footer>
        <div id="wallpaper"></div>
        <%if (session.getAttribute("currentSong") != null) {%>
            <audio onplay="$('playPauseButton').src='images/pause.png'" onpause="$('playPauseButton').src='images/play.png'" id="player" src="http://localhost:8084/<%=((Song)session.getAttribute("currentSong")).getSongId() + ".mp3"%>"></audio>
        <%}%>
    </body>
</html>

