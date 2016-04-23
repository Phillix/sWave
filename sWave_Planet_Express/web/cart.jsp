<%@page import="Dtos.Merch"%>
<%@page import="Daos.MerchDao"%>
<%@page import="Daos.SongDao"%>
<%@page import="Dtos.CartItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="Dtos.Song"%>
<%@page import="Dtos.Ad"%>
<%@page import="Daos.AdDao"%>
<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            if (session == null) {
                response.sendRedirect("login.jsp?refer=cart.jsp");
            }

            User currentUser = (User)session.getAttribute("user");

            String skin = "swave";

            if (currentUser != null) {
                skin = currentUser.getSkin();
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>Cart - sWave</title>
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
        <script src="js/ajax_streamer.js"></script>
    </head>
    <body onload="loadUserPicture(<%=currentUser.getUserId()%>, $('userPic')); resumePlay()">
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
                <a href="playing.jsp">Music</a>
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
                        response.sendRedirect("login.jsp?refer=cart.jsp");
                %>
                    <!-- In case the redirect fails for any reason provide a link -->
                    <a href="login.jsp">Log In</a>
                <%}%>
            </div>
        </header>
        <aside class="panel" id="left_sidebar">
            <div id="visualizer"></div>
        </aside>
        <div id="midsection">
            <h1>My Cart</h1>
            <a href="creditCard.jsp">Proceed To Checkout</a>
            <ul>
            <%
                if (session.getAttribute("cart") != null) {
                    ArrayList<CartItem> cart = (ArrayList<CartItem>)session.getAttribute("cart");
                    for (CartItem c : cart) {
                        Object x;

                        if (c.getType()) {
                            SongDao sdao = new SongDao();
                            x = sdao.getSongById(c.getProdId());
                        }
                        else {
                            MerchDao mdao = new MerchDao();
                            x = mdao.getMerchById(c.getProdId());
                        }
                    %>
                        <li>
                            <%if (x instanceof Song) {%>
                            <h3>Song:</h3>
                            <span>Title: <%=((Song)x).getTitle()%></span><br/>
                            <span>Artist: <%=((Song)x).getArtist()%></span><br/>
                            <%} else {%>
                                <h3>Merch Item:</h3>
                                <span>Name: <%=((Merch)x).getTitle()%></span><br/>
                                <span>Quantity: <%=c.getQty()%></span><br/>
                            <%}%>
                            <%NumberFormat f = NumberFormat.getCurrencyInstance();%>
                            <span>Price: <%=f.format(c.getPrice())%></span>
                        </li>
                <%}
                }
            %>
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
        <div id="wallpaper"></div>
    </body>
</html>

