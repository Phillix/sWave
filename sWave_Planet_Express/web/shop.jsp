<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page import="Daos.SongDao"%>
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
        <%
            User currentUser     = null;
            String skin          = sWave.Server.DEFAULT_SKIN;
            Locale currentLocale = new Locale("en");

            if (session == null || (User)session.getAttribute("user") == null)
                response.sendRedirect("login.jsp?refer=shop.jsp");
            else {
                currentUser   = (User)session.getAttribute("user");
                skin          = currentUser.getSkin();
                currentLocale = new Locale(currentUser.getLangPref());
            }

            final boolean DEBUG = sWave.Server.DEBUGGING;

            if (request.getParameter("addedToCart") != null && request.getParameter("addedToCart").equals("yes")) {
                %><script>alert("Added to Cart");</script><%
            }

            ResourceBundle messages = ResourceBundle.getBundle("i18n.content", currentLocale);
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>Shop - sWave</title>
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
        <script src="js/image_loader.js"></script>
    </head>
    <body onload="loadUserPicture(<%=currentUser.getUserId()%>, $('userPic')); resumePlay();">
        <header class="panel" id="topbar">
            <%=sWave.Graphics.getLogo()%>
            <nav>
                <!-- Bunching up the anchor tags removes the gaps between them caused by the tabbing and inline-block -->
                <a href="playing.jsp"><%=messages.getString("musicNavVar")%></a><a class="currentPageLink" href="shop.jsp"><%=messages.getString("shopNavVar")%></a><a href="account.jsp"><%=messages.getString("accountNavVar")%></a><a href="about.jsp"><%=messages.getString("aboutNavVar")%></a>
            </nav>
            <form id="searchBox" action="UserActionServlet" method="POST">
                <input type="hidden" name="action" value="search"/>
                <input type="search" class="text" name="searchterm" placeholder="Search"/>
            </form>
            <%=sWave.Graphics.s_cart%>
            <img id="userPic" onclick="showHideUserMenu()" width="50" height="50" src="images/test.png"/>
            <div id="userMenu" class="panel">
                <a id="userNameDisplay" href="account.jsp?view=profile"><%=currentUser.getUsername()%></a>
                <form id="logOutButton" action="UserActionServlet" method="POST">
                    <input type="hidden" name="action" value="logout"/>
                    <input class="button" type="submit" value="Log Out"/>
                </form>
            </div>
        </header>
        <aside class="panel" id="left_sidebar">
            <a href="cart.jsp"><%=messages.getString("goToCartVar")%></a>
            <div id="visualizer"></div>
        </aside>
        <div id="midSectionNoPadding" class="noPadding">
            <div id="midUnderlayOmni" class="panel"></div>
            <div id="omniBar" class="panel"></div>
            <ul id="itemList">
                <%
                MerchDao dao = new MerchDao();
                NumberFormat f = NumberFormat.getCurrencyInstance();
                for (Merch m : dao.viewMerchAlpha()) {%>
                    <li class="panel listing songListing">
                        <img class="artwork" alt="<%=messages.getString("pictureOfVar")%> <%=m.getTitle()%>" src="images/merch/<%=m.getMerchId()%>.jpg"/>
                        <span class="songTitle">
                            <%if (DEBUG) {%>
                                <%=m.getMerchId()%>
                            <%}%>
                            <%=m.getTitle()%>
                        </span><br/><br/>
                        <span class="songArtist">
                            <a href="product.jsp?item=<%=m.getMerchId()%>"><%=messages.getString("viewItemVar")%></a>
                        </span>
                        <br/>
                        <div class="listingRight">
                            <%=f.format(m.getPrice())%>
                        </div>
                    </li>
            <%}%>
            </ul>
        </div>
        <%=sWave.UI.footer%>
        <div id="notifier" class="panel"></div>
        <div id="wallpaper"></div>
    </body>
</html>

