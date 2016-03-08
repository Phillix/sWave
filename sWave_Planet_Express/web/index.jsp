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
        <script src="js/visualizer.js"></script>
        <script>
            function updateTime() {
                $("trackTimer").innerHTML = formatTime($("player").currentTime) + " / " + formatTime($("player").duration);
                $("progress").style.width = $("scrubber").style.left = Math.floor($("player").currentTime * ((window.innerWidth - 24) / $("player").duration)) + "px";
                setTimeout("updateTime()", 500);
            }
        </script>
    </head>
    <body onload="initAudioSystem()">
        <header class="panel" id="topbar">
            <img id="header_logo" src="images/logo_black.png" height="60"/>
            <nav>
                <a id="currentPageLink" href="index.jsp?filename=<%=request.getParameter("filename")%>&playid=<%=request.getParameter("playid")%>">Now Playing</a>
                <a href="music.jsp?filename=<%=request.getParameter("filename")%>&playid=<%=request.getParameter("playid")%>">Library</a>
                <a href="temp.html?filename=<%=request.getParameter("filename")%>&playid=<%=request.getParameter("playid")%>">Shop</a>
                <a href="account.jsp?filename=<%=request.getParameter("filename")%>&playid=<%=request.getParameter("playid")%>">Account</a>
                <a href="about.jsp?filename=<%=request.getParameter("filename")%>&playid=<%=request.getParameter("playid")%>">About</a>
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
                Ad ad = ads.getAd((int)Math.floor(Math.random() * ads.getMaxAdId()));
            %>
            <iframe id="ads" src="<%=ad.getAdUrl()%>"></iframe>
        </aside>
        <footer class="panel" id="base">
            <span id="playerStatus">No Data</span>
            <span id="controls">
                <img onclick="playPrevious()" style="width: 30px; height:30px; position:relative; top:-5px; border-radius:40px; border-width:1px; border-style:outset;" src="images/rw.png"/>
                <img onclick="playPause()" style="border-radius:60px; border-width:1px; border-style:outset;" src="images/play.png"/>
                <img onclick="playNext()" style="width: 30px; height:30px; position:relative; top:-5px; border-radius:40px; border-width:1px; border-style:outset;" src="images/fw.png"/>
            </span>
            <span id="trackTimer">
                --:-- / --:--
            </span>
            <span onclick="jumpTo(event)" onmouseover="showScrubber()" onmouseout="hideScrubber()" id="progressBG"></span>
            <span onclick="jumpTo(event)" onmouseover="showScrubber()" onmouseout="hideScrubber()" id="progress"></span>
            <img src="images/scrubber.png" onmouseover="showScrubber()" onmouseout="hideScrubber()" id="scrubber"/>
        </footer>
        <div id="wallpaper"></div>
        <audio id="player" onplay="updateTime()" src="http://localhost:8084/<%=request.getParameter("filename")%>" autoplay></audio>
    </body>
</html>

