<%@page import="Daos.Dao"%>
<%@page import="Dtos.Song"%>
<%@page import="Dtos.Ad"%>
<%@page import="Daos.AdDao"%>
<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (session == null) {
        response.sendRedirect("login.jsp");
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
        <link rel="stylesheet" type="text/css" href="macgril/css/animation.css"/>
        <link rel="stylesheet" type="text/css" href="macgril/css/skins/<%=skin%>/<%=skin%>.css"/>
        <link rel="stylesheet" type="text/css" href="layout/skins/<%=skin%>/base.css"/>
        <link rel="stylesheet" type="text/css" href="layout/skins/<%=skin%>/index.css"/>
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
                <a id="index2Link" class="currentPageLink" href="index.jsp">Music</a>
                <a id="shopLink" href="shop.jsp">Shop</a>
                <a id="accountLink" href="account.jsp">Account</a>
                <a id="aboutLink" href="about.jsp">About</a>
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
                        response.sendRedirect("login.jsp");
                %>
                    <!-- In case the redirect fails for any reason provide a link -->
                    <a href="login.jsp">Log In</a>
                <%}%>
            </div>
        </header>
        <aside class="panel" id="left_sidebar">
            <a id="indexLink" class="currentPageLink" href="index.jsp">
                <h2>Now Playing</h2>
            </a>
            <a id="musicLink" href="music.jsp">
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
            <br/>
            <a id="cartLink" style="margin-left: 20px;" href="cart.jsp">View My Cart</a>
            <%
                AdDao ads = new AdDao();
                int check = (int)Math.ceil(Math.random() * ads.getMaxAdId());
                try {
                    Ad ad = ads.getAd(check);
            %>
            <iframe id="ads" src="<%=ad.getAdUrl()%>"></iframe>
            <%}
            catch (Exception e) {%>
                <script>alert("The Database could not be Reached")</script>
            <%}%>
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
            <audio id="player" src="<%=sWave.Server.PROTOCOL + sWave.Server.DOMAIN + ":" + sWave.Server.TOMCAT_PORT + "/" + ((Song)session.getAttribute("currentSong")).getSongId() + ".mp3"%>"></audio>
            <%if (request.getParameter("time") != null) {%>
                <script>$("player").currentTime = <%=request.getParameter("time")%></script>
            <%}%>
        <%}%>
    </body>
</html>

