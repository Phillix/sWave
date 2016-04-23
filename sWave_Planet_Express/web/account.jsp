<%@page import="Daos.FriendDao"%>
<%@page import="Dtos.Friend"%>
<%@page import="Daos.SongDao"%>
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
        <script src="js/sWaveScripts.js"></script>
        <script src="js/ajax_image_loader.js"></script>
        <script src="js/ajax_uploader.js"></script>
        <script src="js/ajax_streamer.js"></script>
    </head>
    <body onload="loadUserPicture(<%=currentUser.getUserId()%>, $('userPic'), $('largeUserPic')); resumePlay()">
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
                <!-- Bunching up the anchor tags removes the gaps between them caused by the tabbing and inline-block -->
                <a href="playing.jsp">Music</a><a href="shop.jsp">Shop</a><a class="currentPageLink" href="account.jsp">Account</a><a href="about.jsp">About</a>
            </nav>
            <form id="searchBox" action="UserActionServlet" method="POST">
                <input type="hidden" name="action" value="search"/>
                <input type="search" name="searchterm" placeholder="Search"/>
            </form>
            <img id="userPic" onclick="showHideUserMenu()" width="50" height="50" src="images/test.png"/>
            <div id="userMenu" class="panel">
                <a id="userNameDisplay" href="account.jsp?view=profile"><%=currentUser.getUsername()%></a>
                <form id="logOutButton" action="UserActionServlet" method="POST">
                    <input type="hidden" name="action" value="logout"/>
                    <input type="submit" value="Log Out"/>
                </form>
            </div>
        </header>
        <aside class="panel" id="left_sidebar">
            <a href="account.jsp?view=profile">Profile</a>
            <a href="account.jsp?view=orders">Orders</a>
            <a href="account.jsp?view=tickets">Tickets</a>
            <a href="account.jsp?view=settings">Settings</a>
            <%if (currentUser != null && currentUser.isIsAdmin()) {%>
                <a href="account.jsp?view=admin">Admin</a>
            <%}%>
            <div id="visualizer"></div>
        </aside>
        <div id="midSectionInside">
            <%if (request.getParameter("view") != null && currentUser != null) {
                if (request.getParameter("view").equals("profile")) {%>
                    <div id="profileSidebar">
                        <img id="largeUserPic" src="images/test.png" width="200" height="200"/>
                        <h2 id="nameDisplay"><%=(currentUser.getFname() + " " + currentUser.getLname())%></h2>
                        <h5><u>Upload New Picture</u></h5>
                        <input id="userPicField" onchange="uploadUserPicture(<%=currentUser.getUserId()%>)" type="file" accept="image/*" name="userPicField"/><br/>
                        <progress id="uploadProgress2" max="100" value="0"></progress><br/>
                        <span id="progressInfo2"></span><br/>
                    </div>
                    <h1><%=currentUser.getFname()%>'s Profile</h1>
                    <form action="UserActionServlet" method="POST" id="profileDetailsForm">
                        <input type="hidden" name="action" value="updateDetails"/>
                        <label>Username: </label><input name="username" type="text" placeholder="Username" value="<%=currentUser.getUsername()%>"/><br/><br/>
                        <label>Email: </label><input name="email" type="text" placeholder="Email" pattern="(.*)(\@)(.*)[.][a-z]{2,3}$" value="<%=currentUser.getEmail()%>"/><br/><br/>
                        <label>First Name: </label><input pattern="^[A-Z]{1}[a-z]{2,19}$" name="fname" type="text" placeholder="First Name" value="<%=currentUser.getFname()%>"/><br/><br/>
                        <label>Last Name: </label><input pattern="^[A-Z]{1}[a-z]{2,19}$" name="lname" type="text" placeholder="Last Name" value="<%=currentUser.getLname()%>"/><br/><br/>
                        <label>Address Line 1: </label><input type="text" name="add1" placeholder="Address Line 1" value="<%=currentUser.getAdd1()%>"/><br/><br/>
                        <label>Address Line 2: </label><input type="text" name="add2" placeholder="Address Line 2" value="<%=currentUser.getAdd2()%>"/><br/><br/>
                        <label>City: </label><input type="text" name="city" placeholder="City" value="<%=currentUser.getCity()%>"/><br/><br/>
                        <label>County: </label><input type="text" name="county" placeholder="County" value="<%=currentUser.getCounty()%>"/><br/><br/>
                        <input type="submit" value="Update Details"/>
                    </form><br/><br/>
                    <h1>Friends</h1>
                    <table>
                        <tr>
                        <%
                            FriendDao fdao = new FriendDao();
                            ArrayList<Friend> friends = fdao.getUserFriends(currentUser.getUserId());
                            for (Friend f : friends) {
                        %>
                        <td>
                            <img id="face<%=f.getUserId()%>" width="100" height="100"/>
                            <script>loadUserPicture(<%=f.getUserId()%>, $("face<%=f.getUserId()%>"))</script>
                        </td>
                        <%}%>
                        </tr>
                    </table>
                <%} else if (request.getParameter("view").equals("orders")) {%>
                    <h1>My Orders</h1>
                    <ul>
                        <%OrderDao orders = new OrderDao();
                        SongDao songs = new SongDao();
                        int count = 1;
                        for (UltimateOrder theOrder : orders.getFullOrders(currentUser.getUserId())) {%>
                        <li class="listing <%if (count % 2 == 0) {%>listingEven<%}%>">
                            <br/>
                            <%NumberFormat f = NumberFormat.getCurrencyInstance();%>
                            <table style="padding:10px;" width="100%">
                                <tr>
                                    <td><strong>Date:</strong> <%=theOrder.getDateOrdered()%></td>
                                    <td><strong>Songs:</strong> <%=theOrder.getSongSize()%></td>
                                    <td><strong>Merch:</strong> <%=theOrder.getMerchSize()%></td>
                                </tr>
                                <tr>
                                    <td>&#160;</td>
                                    <td>&#160;</td>
                                    <td>&#160;</td>
                                </tr>
                                <%for(int i = 0; i < theOrder.getSongSize(); i++) {
                                    Song s = songs.getSongById(theOrder.getSongId(i));%>
                                    <tr>
                                        <td><%=s.getTitle()%></td>
                                        <td><%=s.getArtist()%></td>
                                        <td><%=f.format(theOrder.getSongPrice(i))%></td>
                                    </tr>
                                <%}%>
                                <tr>
                                    <td>&#160;</td>
                                    <td>&#160;</td>
                                    <td>&#160;</td>
                                </tr>
                                <%for(int i = 0; i < theOrder.getMerchSize(); i++) {%>
                                    <tr>
                                        <td><%=theOrder.getTitle(i)%></td>
                                        <td><%=theOrder.getQty(i)%></td>
                                        <td><%=f.format(theOrder.getMerchPrice(i))%></td>
                                    </tr>
                                <%}%>
                                <tr>
                                    <td>&#160;</td>
                                    <td>&#160;</td>
                                    <td><%=f.format(theOrder.calcTotal())%> <strong>Total</strong></td>
                                </tr>
                            </table>
                        </li>
                <% count++;}%></ul><%
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
                        <form action="UserActionServlet" method="POST">
                            <input type="hidden" name="action" value="changeSkin"/>
                            <!--
                                The skin field must have custom inline CSS as we 
                                don't want it to be affected by differences in 
                                skin due to previews otherwise when previewing 
                                skins the selector might move around and create 
                                a horrible experience.
                            -->
                            <select name="skin" style="position:fixed; top: 80px; left: 220px;">
                                <option onmouseover="previewSkin('swave')" <%if (currentUser.getSkin().toLowerCase().equals("swave")) {%>selected="selected"<%}%>>sWave</option>
                                <option onmouseover="previewSkin('flat')" <%if (currentUser.getSkin().toLowerCase().equals("flat")) {%>selected="selected"<%}%>>Flat</option>
                                <option onmouseover="previewSkin('flat_darkness')" <%if (currentUser.getSkin().toLowerCase().equals("flat_darkness")) {%>selected="selected"<%}%>>Flat Darkness</option>
                                <option onmouseover="previewSkin('nova')" <%if (currentUser.getSkin().toLowerCase().equals("nova")) {%>selected="selected"<%}%>>Nova</option>
                                <option onmouseover="previewSkin('quantum')" <%if (currentUser.getSkin().toLowerCase().equals("quantum")) {%>selected="selected"<%}%>>Quantum</option>
                                <option onmouseover="previewSkin('evolved')" <%if (currentUser.getSkin().toLowerCase().equals("evolved")) {%>selected="selected"<%}%>>Evolved</option>
                                <option onmouseover="previewSkin('legacy')" <%if (currentUser.getSkin().toLowerCase().equals("legacy")) {%>selected="selected"<%}%>>Legacy</option>
                                <option onmouseover="previewSkin('shire')" <%if (currentUser.getSkin().toLowerCase().equals("shire")) {%>selected="selected"<%}%>>Shire</option>
                                <option onmouseover="previewSkin('smart')" <%if (currentUser.getSkin().toLowerCase().equals("smart")) {%>selected="selected"<%}%>>sMart</option>
                                <option onmouseover="previewSkin('smart++')" <%if (currentUser.getSkin().toLowerCase().equals("smar++")) {%>selected="selected"<%}%>>sMart++</option>
                                <option onmouseover="previewSkin('1337')" <%if (currentUser.getSkin().toLowerCase().equals("1337")) {%>selected="selected"<%}%>>1337</option>
                                <option onmouseover="previewSkin('9x')" <%if (currentUser.getSkin().toLowerCase().equals("9x")) {%>selected="selected"<%}%>>9x</option>
                            </select>
                                <br/><br/>
                            <input type="submit" value="Apply"/><br/><br/>
                        </form>
                        <em>Your Skin Preference will stored in our database and maintained across machines.</em>
                        <br/><br/>
                        <strong>NOTE:</strong> The following settings are LOCAL to your machine, you will need to <em><u>export</u></em> these settings to use them on another machine.
                        <br/><br/>
                        <button>Import Settings</button>&#160;<button>Export Settings</button><br/><br/>
                        <label>Screensaver</label><input type="checkbox"/><br/>
                    <%} else if (request.getParameter("view").equals("admin")) {
                            if (currentUser.isIsAdmin()) {%>
                                <h1>Admin Panel</h1>
                                <h3>System</h3>
                                <h5>CPUs: <%=sWave.Server.CPUs%></h5>
                                <h5>JVM Heap: <%=sWave.Server.JVMHEAP%></h5>
                                <h3>Upload Tracks</h3>
                                <h5><u>Note: Only audio files under 16MB can be uploaded.</u></h5>
                                <h5><u>Note: You may upload up to 100MB at a time.</u></h5>
                                <input id="fileSelector" type="file" name="songBlob" accept="audio/mpeg" onchange="showSizes()" multiple/><br/>
                                <span id="fileSizes"></span>
                                <button onclick="uploadSongs()">Upload</button>
                                <progress id="uploadProgress" max="100" value="0"></progress>
                                <span id="progressInfo"></span>
                            <%} else {
                                response.sendRedirect("noperm.jsp");
                              }
                    }
            }%>
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
