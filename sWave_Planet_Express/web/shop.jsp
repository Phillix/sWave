<%@page import="java.text.NumberFormat"%>
<%@page import="Dtos.Merch"%>
<%@page import="Daos.MerchDao"%>
<%@page import="Dtos.Song"%>
<%@page import="Dtos.Ad"%>
<%@page import="Daos.AdDao"%>
<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%if (session == null) {
                response.sendRedirect("login.jsp");
            }

            User currentUser = (User)session.getAttribute("user");
            String skin = "flat";

            if (currentUser != null) {
                skin = currentUser.getSkin();
            }
            final boolean DEBUG = sWave.Debugging.debug;

            if (request.getParameter("addedToCart") != null && request.getParameter("addedToCart").equals("yes")) {
                %><script>alert("Added to Cart")</script><%
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>Shop - sWave</title>
        <link rel="stylesheet" type="text/css" href="layout/base.css"/>
        <link rel="stylesheet" type="text/css" href="macgril/css/base.css"/>
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
                <a id="indexLink" href="index.jsp">Music</a>
                <a id="shopLink" class="currentPageLink" href="shop.jsp">Shop</a>
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
            <span id="copyNotice">
                Copyright &copy; 2016<br/>
                Team Planet Express<br/>
            </span>
            <div id="visualizer"></div>
        </aside>
        <div id="midsection">
        <table>
            <%
            MerchDao dao = new MerchDao();
            for (Merch m : dao.viewMerchAlpha()) {%>
                <tr>
                    <%if (DEBUG) {%>
                        <td><%=m.getMerchId()%></td>
                    <%}%>
                    <td><%=m.getTitle()%></td>
                    <%NumberFormat f = NumberFormat.getCurrencyInstance();%>
                    <td><%=f.format(m.getPrice())%></td>
                    <td><form action="UserActionServlet" method="POST">
                            <input type="hidden" name="action" value="addMerchToCart"/>
                            <input type="hidden" name="merchid" value="<%=m.getMerchId()%>"/>
                            <input type="hidden" name="price" value="<%=m.getPrice()%>"/>
                            <input type="number" value="1" min="1" name="qty"/>
                            <input type="submit" value="Add to Cart"/>
                        </form>
                    </td>
                </tr>
        <%}%>
        </table>
        </div>
        <aside class="panel" id="right_sidebar">
            <br/>
            <a id="cartLink" style="margin-left: 20px;" href="cart.jsp">View My Cart</a>
            <%
                AdDao ads = new AdDao();
                Ad ad = ads.getAd((int)Math.ceil(Math.random() * ads.getMaxAdId()));
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
            <audio id="player" src="<%=sWave.Server.PROTOCOL + sWave.Server.DOMAIN + ":" + sWave.Server.TOMCAT_PORT + "/" + ((Song)session.getAttribute("currentSong")).getSongId() + ".mp3"%>"></audio>
            <%if (request.getParameter("time") != null) {%>
                <script>$("player").currentTime = <%=request.getParameter("time")%></script>
            <%}%>
        <%}%>
    </body>
</html>

