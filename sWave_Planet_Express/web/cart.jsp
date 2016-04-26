<%@page import="Dtos.Merch"%>
<%@page import="Daos.MerchDao"%>
<%@page import="Daos.SongDao"%>
<%@page import="Dtos.CartItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="Dtos.Song"%>
<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            User currentUser = null;
            String skin = "swave";

            if (session == null || (User)session.getAttribute("user") == null)
                response.sendRedirect("login.jsp?refer=cart.jsp");
            else {
                currentUser = (User)session.getAttribute("user");
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
        <script src="macgril/js/audio.js"></script>
        <script src="macgril/js/notifications.js"></script>
        <script src="js/three.min.js"></script>
        <script src="js/audio_system.js"></script>
        <script src="js/streamer.js"></script>
    </head>
    <body onload="loadUserPicture(<%=currentUser.getUserId()%>, $('userPic')); resumePlay()">
        <header class="panel" id="topbar">
            <%=sWave.Graphics.getLogo()%>
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
            <a href="javascript:history.back()">Back</a>
            <a href="shop.jsp">Go to Shop</a>
            <div id="visualizer"></div>
        </aside>
        <div id="midsection">
            <div id="midUnderlay" class="panel"></div>
            <h1>My Cart</h1>
            <a href="checkout.jsp">Proceed To Checkout</a>
            <ul>
            <%
                if (session.getAttribute("cart") != null) {
                    ArrayList<CartItem> cart = (ArrayList<CartItem>)session.getAttribute("cart");
                    NumberFormat f = NumberFormat.getCurrencyInstance();
                    for (CartItem c : cart) {
                        Object x;

                        if (c.getType()) {
                            SongDao sdao = new SongDao();
                            x = sdao.getSongById(c.getProdId());
                        }
                        else {
                            MerchDao mdao = new MerchDao();
                            System.out.println(c.getProdId());
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
                            <span>Price: <%=f.format(c.getPrice())%></span>
                        </li>
                <%}
                }
            %>
            </ul>
        </div>
        <%=sWave.UI.footer%>
        <div id="notifier" class="panel"></div>
        <div id="wallpaper"></div>
    </body>
</html>

