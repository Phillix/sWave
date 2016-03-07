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
        <title>About sWave</title>
        <link rel="stylesheet" type="text/css" href="main.css"/>
        <link rel="stylesheet" type="text/css" href="macgril/css/base.css"/>
        <%
            String skin = "flat";
            if (currentUser != null) {
                skin = currentUser.getSkin();
            }
        %>
        <link rel="stylesheet" type="text/css" href="macgril/css/skins/<%=skin%>/<%=skin%>.css"/>
        <script src="macgril/js/audio.js"></script>
        <script src="js/three.min.js"></script>
        <script src="js/visualizer.js"></script>
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
            <h1>Project sWave</h1>
            <h3>Brought to you by Team Planet Express</h3>
            Planet Express is:
            <ul>
                <li>Austin Foley</li>
                <li>Brian Millar</li>
                <li>Philip Carey</li>
            </ul>
        </div>
        <aside class="panel" id="right_sidebar">
            <%
                AdDao ads = new AdDao();
                Ad ad = ads.getAd((int)Math.floor(Math.random() * ads.getMaxAdId()));
            %>
            <iframe id="ads" src="<%=ad.getAdUrl()%>"></iframe>
        </aside>
        <footer class="panel" id="base">
        </footer>
        <div id="wallpaper"></div>
        <audio id="player" src="http://localhost:8084/<%=request.getParameter("filename")%>" autoplay></audio>
    </body>
</html>

