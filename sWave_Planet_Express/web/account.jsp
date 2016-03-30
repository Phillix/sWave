<%@page import="Dtos.Order"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="Dtos.UltimateOrder"%>
<%@page import="Daos.OrderDao"%>
<%@page import="Dtos.Song"%>
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
        <%
            if (request.getParameter("view") == null) {
                response.sendRedirect("account.jsp?view=profile");
            }
            
            if (session == null) {
                response.sendRedirect("login.jsp?refer=account.jsp&view=" + request.getParameter("view"));
            }
            
            User currentUser = (User)session.getAttribute("user");

            String skin = "swave";

            if (currentUser != null) {
                skin = currentUser.getSkin();
            }

            final boolean DEBUG = sWave.Server.DEBUGGING;
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>Account - sWave</title>
        <link rel="stylesheet" type="text/css" href="macgril/css/animation.css"/>
        <link rel="stylesheet" type="text/css" href="macgril/css/skins/<%=skin%>/<%=skin%>.css"/>
        <link rel="stylesheet" type="text/css" href="layout/skins/<%=skin%>/base.css"/>
        <link rel="stylesheet" type="text/css" href="layout/skins/<%=skin%>/account.css"/>
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
            <svg id="header_logo" width="194" height="60" viewBox="0 0 300 100">
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
                <a id="indexLink" href="index.jsp">Music</a>
                <a id="shopLink" href="shop.jsp">Shop</a>
                <a id="accountLink" class="currentPageLink" href="account.jsp">Account</a>
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
                        response.sendRedirect("login.jsp?refer=account.jsp?view=" + request.getParameter("view"));
                %>
                    <!-- In case the redirect fails for any reason provide a link -->
                    <a href="login.jsp">Log In</a>
                <%}%>
            </div>
        </header>
        <aside class="panel" id="left_sidebar">
            <a href="account.jsp?view=profile"><h2>Profile</h2></a>
            <a href="account.jsp?view=orders"><h2>Orders</h2></a>
            <a href="account.jsp?view=tickets"><h2>Tickets</h2></a>
            <a href="account.jsp?view=settings"><h2>Settings</h2></a>
            <%if (currentUser != null && currentUser.isIsAdmin()) {%>
                <a href="account.jsp?view=admin"><h2>Admin</h2></a>
            <%}%>
            <span id="copyNotice">
                Copyright &copy; 2016<br/>
                Team Planet Express<br/>
            </span>
            <div id="visualizer"></div>
        </aside>
        <div id="midsection">
            <%if (request.getParameter("view") != null && currentUser != null) {
                if (request.getParameter("view").equals("profile")) {%>
                    <h3>Username: <%=currentUser.getUsername()%></h3>
                    <h3>Full Name: <%=currentUser.getFname() + " " + currentUser.getLname()%></h3>
                    <h3>Email: <%=currentUser.getEmail()%></h3>
                    <h3>Address:<br/>
                        <%=currentUser.getAdd1()%>,<br/>
                        <%=currentUser.getAdd2()%>,<br/>
                        <%=currentUser.getCity()%>,<br/>
                        <%=currentUser.getCounty()%><br/></h3>
                    <br/>
                    <h2>Edit Details</h2>
                    <form action="UserActionServlet" method="POST">
                        <input type="hidden" name="action" value="updateDetails"/>
                        <input name="username" type="text" placeholder="Username"/><br/><br/>
                        <input name="email" type="text" placeholder="Email" pattern="(.*)(\@)(.*)[.][a-z]{2,3}$"/><br/><br/>
                        <input pattern="^[A-Z]{1}[a-z]{2,19}$" name="fname" type="text" placeholder="First Name"/><br/><br/>
                        <input pattern="^[A-Z]{1}[a-z]{2,19}$" name="lname" type="text" placeholder="Last Name"/><br/><br/>
                        <input type="text" name="add1" placeholder="Address Line 1"/><br/><br/>
                        <input type="text" name="add2" placeholder="Address Line 2"/><br/><br/>
                        <input type="text" name="city" placeholder="City"/><br/><br/>
                        <input type="text" name="county" placeholder="County"/><br/><br/>
                        <input type="submit" value="Update"/>
                    </form><br/><br/>
                <%} else if (request.getParameter("view").equals("orders")) {%>
                    <h1>My Orders</h1>
                    <ul>
                    <%OrderDao orders = new OrderDao();
                    for (UltimateOrder theOrder : orders.getFullOrders(currentUser.getUserId())) {%>
                    <li>
                        Date: <%=theOrder.getDateOrdered()%><br/>
                        <%NumberFormat f = NumberFormat.getCurrencyInstance();%>
                        Total: <%=f.format(theOrder.calcTotal())%><br/>
                        Songs: <%=theOrder.getSongSize()%><br/>
                        <%for(int i = 0; i < theOrder.getSongSize(); i++) {%>
                        <%=i+1%>: <%=f.format(theOrder.getSongPrice(i))%><br/>
                        <%}%>
                        Merch: <%=theOrder.getMerchSize()%><br/>
                        <%for(int i = 0; i < theOrder.getMerchSize(); i++) {%>
                        title: <%=theOrder.getTitle(i)%>  price: <%=f.format(theOrder.getMerchPrice(i))%> quantity: <%=theOrder.getQty(i)%>      
                        <%}%>
                        <hr/>
                    </li>
                <%}%></ul><%
                } else if (request.getParameter("view").equals("tickets")) {
                    if (request.getParameter("ticketView") != null && request.getParameter("ticketView").equals("closed")) {%>
                        <h1>Closed Tickets</h1>
                        <a href="account.jsp?view=tickets">Open Tickets</a>
                    <%} else {%>
                        <h1>Open Tickets</h1>
                        <a href="account.jsp?view=tickets&ticketView=closed">Closed Tickets</a>
                    <%}%>
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
                        for (Ticket t : tickDao.getAllTickets()) {
                            if (!t.isResolved() ^ (request.getParameter("ticketView") != null && request.getParameter("ticketView").equals("closed"))) {
                            if (currentUser.isIsAdmin()) {%>
                                <ul class="ticketList">
                                    <li class="panel ticket">
                                        <h3 class="ticketHeader">Ticket <%=t.getTicketId()%><span class="ticketHeaderRight"><a href="mailto:<%=userDao.getUserById(t.getUserId()).getEmail()%>" target="_blank"><u><%=userDao.getUserById(t.getUserId()).getUsername()%></u></a>&#160;&#160;&#160;<u><%=t.getDateRaised()%></u></span></h3>
                                        <br/>
                                        <p class="ticketIssue">
                                            <%=t.getIssue()%>
                                        </p>
                                        <br/>
                                        <form style="margin-left:10px;" method="POST" action="UserActionServlet">
                                            <input type="hidden" name="action" value="closeTicket"/>
                                            <input type="hidden" name="ticketId" value="<%=t.getTicketId()%>"/>
                                            <input type="submit" value="Close"/>
                                        </form>
                                        <br/>
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
                        }
                   } else if (request.getParameter("view").equals("settings")) {%>
                        Current Skin: <%=currentUser.getSkin()%>
                        <form action="UserActionServlet" method="POST">
                            <input type="hidden" name="action" value="changeSkin"/>
                            <label>Select Skin: </label>
                            <select name="skin">
                                <option>sWave</option>
                                <option>Flat</option>
                                <option>Flat Darkness</option>
                                <option>Nova</option>
                                <option>Quantum</option>
                                <option>Evolved</option>
                                <option>Legacy</option>
                                <option>Shire</option>
                                <option>sMart</option>
                                <option>sMart++</option>
                                <option>1337</option>
                                <option>9x</option>
                            </select>
                            <input type="submit" value="Apply"/>
                        </form>
                        <em>Your Skin Preference will stored in our database and maintained across machines.</em>
                        <br/><br/>
                        <strong>NOTE:</strong> The following settings are LOCAL to your machine, you will need to <em><u>export</u></em> these settings to use them on another machine.
                        <br/><br/>
                        <button>Import Settings</button>&#160;<button>Export Settings</button><br/><br/>
                        <label>Screensaver</label><input type="checkbox"/><br/>
                    <%} else if (request.getParameter("view").equals("admin")) {
                            if (currentUser.isIsAdmin()) {%>
                                <iframe style="border:none; width:500px; height:400px;" src="upload.jsp"></iframe>
                            <%} else {
                                response.sendRedirect("noperm.jsp");
                              }
                    }
            }%>
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
                <svg width="45" height="45" viewBox="0 0 100 100">
                    <circle class="iconCircleStroked" cx="50" cy="50" r="40"/>
                    <polygon class="iconPolyFilled" points="72.5,35 72.5,65 47.5,50"/>
                    <polygon class="iconPolyFilled" points="47.5,35 47.5,65 22.5,50"/>
                </svg>
                <svg id="playPauseButton" width="50" height="50" onclick="playPause()" viewBox="0 0 100 100">
                    <circle class="iconCircleStroked" cx="50" cy="50" r="40"/>
                    <polygon class="iconPolyFilled" id="playButton" points="33,25 33,75 80,50"/>
                    <rect class="iconRectFilled" id="pauseButton1" x="35" y="25" width="10" height="50"/>
                    <rect class="iconRectFilled" id="pauseButton2" x="55" y="25" width="10" height="50"/>
                </svg>
                <svg width="45" height="45" viewBox="0 0 100 100">
                    <circle class="iconCircleStroked" cx="50" cy="50" r="40"/>
                    <polygon class="iconPolyFilled" points="27.5,35 27.5,65 52.5,50"/>
                    <polygon class="iconPolyFilled" points="52.5,35 52.5,65 77.5,50"/>
                </svg>
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
