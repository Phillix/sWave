<%@page import="Dtos.Ad"%>
<%@page import="Daos.AdDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Daos.TicketDao"%>
<%@page import="Dtos.Ticket"%>
<%@page import="Dtos.Ticket"%>
<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%User currentUser = (User)session.getAttribute("user");%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Browse Music</title>
        <link rel="stylesheet" type="text/css" href="macgril/css/base.css"/>
        <link rel="stylesheet" type="text/css" href="main.css"/>
        <%
            String skin = "flat";
            if (currentUser != null) {
                skin = currentUser.getSkin();
            }
            
            final boolean DEBUG = Debugging.Debug.debug;
        %>
        <link rel="stylesheet" type="text/css" href="macgril/css/skins/<%=skin%>/<%=skin%>.css"/>
        <script src="macgril/js/dom.js"></script>
        <script src="macgril/js/io.js"></script>
    </head>
    <body onload="$('player').currentTime = lStore('currentTime')">
        <header class="panel" id="topbar">
            <img id="header_logo" src="images/logo_black.png" height="60"/>
            <nav>
                <a href="index.jsp?filename=<%=request.getParameter("filename")%>&playid=<%=request.getParameter("playid")%>">Now Playing</a>
                <a href="music.jsp?filename=<%=request.getParameter("filename")%>&playid=<%=request.getParameter("playid")%>">Library</a>
                <a href="temp.html?filename=<%=request.getParameter("filename")%>&playid=<%=request.getParameter("playid")%>">Shop</a>
                <a id="currentPageLink" href="account.jsp?filename=<%=request.getParameter("filename")%>&playid=<%=request.getParameter("playid")%>">Account</a>
                <a href="temp.html?filename=<%=request.getParameter("filename")%>&playid=<%=request.getParameter("playid")%>">About</a>
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
            <h2>Profile</h2>
            <h2>Tickets</h2>
            <h2>Settings</h2>
            <h2>Admin</h2>
        </aside>
        <div id="midsection">
            <div id="profile">
                Username: <%=currentUser.getUsername()%><br/>
                Full Name: <%=currentUser.getFname() + " " + currentUser.getLname()%><br/>
                Email: <%=currentUser.getEmail()%><br/>
            </div>
            <div id="tickets">
                <h1>Tickets</h1>
                <button id="cancelNewTicketButton" style="display:none;" onclick="$('newTicketForm').style.display='none'; $('cancelNewTicketButton').style.display='none'; $('newTicketButton').style.display='block'">Cancel</button>
                <button id="newTicketButton" onclick="$('newTicketForm').style.display='block'; $('newTicketButton').style.display='none'; $('cancelNewTicketButton').style.display='block'">Open Ticket</button><br/>
                <form id="newTicketForm" action="UserActionServlet" method="POST">
                    <input type="hidden" name="action" value="createTicket"/>
                    <input type="hidden" name="userId" value="<%=currentUser.getUserId()%>"/>
                    <textarea id="ticketIssue" name="issue" placeholder="Use this area to describe your issue"></textarea><br/><br/>
                    <input type="submit" value="Open"/>
                </form>
                <%
                TicketDao tickDao = new TicketDao();
                for (Ticket t : tickDao.getCurrTickets()) {%>
                <ol>
                    <li>
                        <h2>Ticket <%=t.getTicketId()%></h2>
                        <h5>Date: <%=t.getDateRaised()%></h5>
                        <%=t.getIssue()%>
                    </li>
                </ol>
                <%}%>
            </div>
            <div id="settings">
                Current Skin: <%=currentUser.getSkin()%>
            </div>
            <div id="admin">
                <iframe src="upload.jsp"></iframe>
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
        </footer>
        <div id="wallpaper"></div>
        <audio id="player" src="http://localhost:8084/<%=request.getParameter("filename")%>" autoplay></audio>
    </body>
</html>
