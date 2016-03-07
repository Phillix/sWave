<%@page import="Daos.UsersDao"%>
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
        <link rel="icon" type="image/png" href="images/favicon.png">
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
            <a href="account.jsp?view=profile"><h2>Profile</h2></a>
            <a href="account.jsp?view=tickets"><h2>Tickets</h2></a>
            <a href="account.jsp?view=settings"><h2>Settings</h2></a>
            <%if (currentUser.isIsAdmin()) {%>
                <a href="account.jsp?view=admin"><h2>Admin</h2></a>
            <%}%>
        </aside>
        <div id="midsection">
            <%if (request.getParameter("view") != null) {
                if (request.getParameter("view").equals("profile")) {%>
                    Username: <%=currentUser.getUsername()%><br/>
                    Full Name: <%=currentUser.getFname() + " " + currentUser.getLname()%><br/>
                    Email: <%=currentUser.getEmail()%><br/>
                <%} else if (request.getParameter("view").equals("tickets")) {%>
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
                        UsersDao   userDao = new UsersDao();
                        for (Ticket t : tickDao.getCurrTickets()) {
                            if (currentUser.isIsAdmin()) {%>
                                <ul class="ticketList">
                                    <li class="panel ticket">
                                        <h3 class="ticketHeader">Ticket <%=t.getTicketId()%><span class="ticketHeaderRight"><u><%=userDao.getUserById(t.getUserId()).getUsername()%></u>&#160;&#160;&#160;<u><%=t.getDateRaised()%></u></span></h3>
                                        <br/>
                                        <span class="ticketIssue">
                                            <%=t.getIssue()%>
                                        </span>
                                        <br/><br/>
                                    </li>
                                </ul>
                            <%} else {
                                if (t.getUserId() == currentUser.getUserId()) {%>
                                    <ul class="ticketList">
                                        <li class="panel ticket">
                                            <h3 class="ticketHeader">Ticket <%=t.getTicketId()%><span class="ticketHeaderRight"><u><%=t.getDateRaised()%></u></span></h3>
                                            <br/>
                                            <span class="ticketIssue">
                                                <%=t.getIssue()%>
                                            </span>
                                            <br/><br/>
                                        </li>
                                    </ul>
                                <%}
                              }
                        }
                   } else if (request.getParameter("view").equals("settings")) {%>
                        Current Skin: <%=currentUser.getSkin()%>
                        <form action="UserActionServlet" method="POST">
                            <input type="hidden" name="action" value="changeSkin"/>
                            <label>Select Skin: </label>
                            <select name="skin">
                                <option>Flat</option>
                                <option>Flat Darkness</option>
                                <option>Nova</option>
                                <option>Quantum</option>
                            </select>
                            <input type="submit" value="Apply"/>
                        </form>
                    <%} else if (request.getParameter("view").equals("admin")) {
                            if (currentUser.isIsAdmin()) {%>
                                <iframe src="upload.jsp"></iframe>
                            <%} else {
                                response.sendRedirect("noperm.jsp");
                              }
                    }
            }%>
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
