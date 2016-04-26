<%@page import="Daos.MessageDao"%>
<%@page import="Daos.SongDao"%>
<%@page import="Dtos.Song"%>
<%@page import="Daos.FriendDao"%>
<%@page import="Dtos.Friend"%>
<%@page import="Dtos.Order"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="Dtos.UltimateOrder"%>
<%@page import="Daos.OrderDao"%>
<%@page import="Daos.UsersDao"%>
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
            
            User currentUser = null;
            String skin = "swave";

            if (session == null || (User)session.getAttribute("user") == null)
                response.sendRedirect("login.jsp?refer=account.jsp&view=" + request.getParameter("view"));
            else {
                currentUser = (User)session.getAttribute("user");
                skin = currentUser.getSkin();
            }

            final boolean DEBUG = sWave.Server.DEBUGGING;
            
            FriendDao fdao = new FriendDao();
            UsersDao  udao = new UsersDao();
            ArrayList<Friend> pending = fdao.getPendingFriendRequests(currentUser.getUserId());
            ArrayList<Friend> friends = fdao.getUserFriends(currentUser.getUserId());
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
        <script src="macgril/js/audio.js"></script>
        <script src="macgril/js/notifications.js"></script>
        <script src="js/three.min.js"></script>
        <script src="js/audio_system.js"></script>
        <script src="js/scripts.js"></script>
        <script src="js/image_loader.js"></script>
        <script src="js/uploader.js"></script>
        <script src="js/streamer.js"></script>
        <script src="js/chat.js"></script>
    </head>
    <body onload="loadUserPicture(<%=currentUser.getUserId()%>, $('userPic') <%if (request.getParameter("view") != null && request.getParameter("view").equals("profile")) {%>, $('largeUserPic')<%}%>); resumePlay()">
        <header class="panel" id="topbar">
            <%=sWave.Graphics.getLogo()%>
            <nav>
                <!-- Bunching up the anchor tags removes the gaps between them caused by the tabbing and inline-block -->
                <a href="playing.jsp">Music</a><a href="shop.jsp">Shop</a><a class="currentPageLink" href="account.jsp">Account</a><a href="about.jsp">About</a>
            </nav>
            <form id="searchBox" action="UserActionServlet" method="POST">
                <input type="hidden" name="action" value="search"/>
                <input type="search" name="searchterm" placeholder="Search"/>
            </form>
            <%=sWave.Graphics.s_cart%>
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
            <a href="account.jsp?view=profile" <%if (request.getParameter("view") != null && request.getParameter("view").equals("profile")) {%>class="currentPageLink"<%}%>>Profile</a>
            <a href="account.jsp?view=friends" <%if (request.getParameter("view") != null && request.getParameter("view").equals("friends")) {%>class="currentPageLink"<%}%>>Friends</a>
            <a href="account.jsp?view=orders" <%if (request.getParameter("view") != null && request.getParameter("view").equals("orders")) {%>class="currentPageLink"<%}%>>Orders</a>
            <a href="account.jsp?view=tickets" <%if (request.getParameter("view") != null && request.getParameter("view").equals("tickets")) {%>class="currentPageLink"<%}%>>Tickets</a>
            <a href="account.jsp?view=settings" <%if (request.getParameter("view") != null && request.getParameter("view").equals("settings")) {%>class="currentPageLink"<%}%>>Settings</a>
            <%if (currentUser != null && currentUser.isIsAdmin()) {%>
                <a href="account.jsp?view=admin" <%if (request.getParameter("view") != null && request.getParameter("view").equals("admin")) {%>class="currentPageLink"<%}%>>Admin</a>
            <%}%>
            <div id="visualizer"></div>
        </aside>
        <div id="midsection">
            <%if (request.getParameter("view") != null && currentUser != null) {
                if (request.getParameter("view").equals("profile")) {%>
                    <div id="midUnderlay" class="panel"></div>
                    <div id="profileSidebar">
                        <img id="largeUserPic" src="images/test.png" width="200" height="200"/>
                        <h2 id="nameDisplay"><%=(currentUser.getFname() + " " + currentUser.getLname())%></h2>
                        <h5><u>Upload New Picture (MAX 64kb)</u></h5>
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
                        <label>County: </label>
                        <select name="county">
                            <option value="CW" <%if (currentUser.getCounty().equals("CW")) {%>selected<%}%>>Carlow</option>
                            <option value="CN" <%if (currentUser.getCounty().equals("CN")) {%>selected<%}%>>Cavan</option>
                            <option value="CL" <%if (currentUser.getCounty().equals("CL")) {%>selected<%}%>>Clare</option>
                            <option value="C" <%if (currentUser.getCounty().equals("C")) {%>selected<%}%>>Cork</option>
                            <option value="DL" <%if (currentUser.getCounty().equals("DL")) {%>selected<%}%>>Donegal</option>
                            <option value="D" <%if (currentUser.getCounty().equals("D")) {%>selected<%}%>>Dublin</option>
                            <option value="G" <%if (currentUser.getCounty().equals("G")) {%>selected<%}%>>Galway</option>
                            <option value="K" <%if (currentUser.getCounty().equals("K")) {%>selected<%}%>>Kerry</option>
                            <option value="KD" <%if (currentUser.getCounty().equals("KD")) {%>selected<%}%>>Kildare</option>
                            <option value="KK" <%if (currentUser.getCounty().equals("KK")) {%>selected<%}%>>Kilkenny</option>
                            <option value="LS" <%if (currentUser.getCounty().equals("LS")) {%>selected<%}%>>Laois</option>
                            <option value="LM" <%if (currentUser.getCounty().equals("LM")) {%>selected<%}%>>Leitrim</option>
                            <option value="LK" <%if (currentUser.getCounty().equals("LK")) {%>selected<%}%>>Limerick</option>
                            <option value="LF" <%if (currentUser.getCounty().equals("LF")) {%>selected<%}%>>Longford</option>
                            <option value="L" <%if (currentUser.getCounty().equals("L")) {%>selected<%}%>>Louth</option>
                            <option value="M" <%if (currentUser.getCounty().equals("M")) {%>selected<%}%>>Mayo</option>
                            <option value="MH" <%if (currentUser.getCounty().equals("MH")) {%>selected<%}%>>Meath</option>
                            <option value="MO" <%if (currentUser.getCounty().equals("MO")) {%>selected<%}%>>Monaghan</option>
                            <option value="O" <%if (currentUser.getCounty().equals("O")) {%>selected<%}%>>Offaly</option>
                            <option value="R" <%if (currentUser.getCounty().equals("R")) {%>selected<%}%>>Roscommon</option>
                            <option value="S" <%if (currentUser.getCounty().equals("S")) {%>selected<%}%>>Sligo</option>
                            <option value="T" <%if (currentUser.getCounty().equals("T")) {%>selected<%}%>>Tipperary</option>
                            <option value="WF" <%if (currentUser.getCounty().equals("WF")) {%>selected<%}%>>Waterford</option>
                            <option value="WM" <%if (currentUser.getCounty().equals("WM")) {%>selected<%}%>>Westmeath</option>
                            <option value="WX" <%if (currentUser.getCounty().equals("WX")) {%>selected<%}%>>Wexford</option>
                            <option value="W" <%if (currentUser.getCounty().equals("W")) {%>selected<%}%>>Wicklow</option>
                        </select>
                        <br/><br/>
                        <input type="submit" value="Update Details"/>
                    </form>
                <%} else if (request.getParameter("view").equals("orders")) {%>
                    <div id="midUnderlay" class="panel"></div>
                    <h1>My Orders</h1>
                    <ul>
                        <%OrderDao orders = new OrderDao();
                        SongDao songs = new SongDao();
                        int count = 1;
                        NumberFormat f = NumberFormat.getCurrencyInstance();
                        for (UltimateOrder theOrder : orders.getFullOrders(currentUser.getUserId())) {%>
                        <li class="listing <%if (count % 2 == 0) {%>listingEven<%}%>">
                            <br/>
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
                    <div id="midUnderlay" class="panel"></div>
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
                   } else if (request.getParameter("view").equals("friends")) {%>
                        <div id="midUnderlay" class="panel"></div>
                        <h1>Friends</h1>
                        <h3>Requests</h3>
                        <%if (pending.size() == 0) {%>
                            <h3>Nobody wants to be your Friend</h3>
                        <%} else {%>
                            <ul>
                                <%for (Friend f : pending) {%>
                                    <%if (f.getFriendId() == currentUser.getUserId()) {%>
                                        <li>
                                            <img id="face<%=f.getFriendId()%>" src="images/test.png" width="100" height="100"/>
                                            <script>loadUserPicture(<%=f.getUserId()%>, $('face<%=f.getUserId()%>'));</script>
                                            <%=udao.getUserById(f.getUserId()).getFname()%>&#160;
                                            <%=udao.getUserById(f.getUserId()).getLname()%>&#160;
                                            (<%=udao.getUserById(f.getUserId()).getUsername()%>)<br/>
                                            <form action="UserActionServlet" method="POST">
                                                <input type="hidden" name="action" value="confirmFriend"/>
                                                <input type="hidden" name="friendId" value="<%=f.getUserId()%>"/>
                                                <input type="submit" value="Accept"/>
                                            </form>
                                        </li>
                                    <%}%>
                                  <%}%>
                            </ul>
                        <%}%>
                        <hr/>
                        <%if (friends.size() == 0) {%>
                                <h3>You have yet to discover the magic of friendship :^(</h3>
                        <%} else {%>
                        <ul>
                            <%for (Friend f : friends) {%>
                                <%if (f.getStatus() == 'c') {%>
                                    <li class="panel listing songListing">
                                        <img class="artwork" id="face<%if (f.getUserId() == currentUser.getUserId()) {%><%=f.getFriendId()%><%} else {%><%=f.getUserId()%><%}%>2" src="images/test.png" width="100" height="100"/>
                                        <script>loadUserPicture(<%if (f.getUserId() == currentUser.getUserId()) {%><%=f.getFriendId()%><%} else {%><%=f.getUserId()%><%}%>, $('face<%if (f.getUserId() == currentUser.getUserId()) {%><%=f.getFriendId()%><%} else {%><%=f.getUserId()%><%}%>2'))</script>
                                        <span class="songTitle"><%=udao.getUserById(f.getUserId() == currentUser.getUserId() ? f.getFriendId() : f.getUserId()).getFname()%>&#160;<%=udao.getUserById(f.getUserId() == currentUser.getUserId() ? f.getFriendId() : f.getUserId()).getLname()%></span><br/>
                                        <span class="songArtist"><%=udao.getUserById(f.getUserId() == currentUser.getUserId() ? f.getFriendId() : f.getUserId()).getUsername()%></span>
                                        <span class="listingRight">
                                            <form action="UserActionServlet" method="POST">
                                                <input type="hidden" name="action" value="removeFriend"/>
                                                <input type="hidden" name="friendId" value="<%if (f.getUserId() == currentUser.getUserId()) {%><%=f.getFriendId()%><%} else {%><%=f.getUserId()%><%}%>"/>
                                                <input type="submit" class="danger" value="Unfriend"/>
                                            </form>
                                            <button onclick="openChat(<%=f.getFriendId()%>)">Chat</button>
                                        </span>
                                    </li>
                                <%} else if (f.getStatus() == 'p' && f.getUserId() == currentUser.getUserId()) {%>
                                    <li class="panel listing songListing">
                                        <img class="artwork" id="face<%if (f.getUserId() == currentUser.getUserId()) {%><%=f.getFriendId()%><%} else {%><%=f.getUserId()%><%}%>2" src="images/test.png" width="100" height="100"/>
                                        <script>loadUserPicture(<%if (f.getUserId() == currentUser.getUserId()) {%><%=f.getFriendId()%><%} else {%><%=f.getUserId()%><%}%>, $('face<%if (f.getUserId() == currentUser.getUserId()) {%><%=f.getFriendId()%><%} else {%><%=f.getUserId()%><%}%>2'))</script>
                                        <span class="songTitle"><%=udao.getUserById(f.getUserId() == currentUser.getUserId() ? f.getFriendId() : f.getUserId()).getFname()%>&#160;<%=udao.getUserById(f.getUserId() == currentUser.getUserId() ? f.getFriendId() : f.getUserId()).getLname()%></span><br/>
                                        <span class="songArtist"><%=udao.getUserById(f.getUserId() == currentUser.getUserId() ? f.getFriendId() : f.getUserId()).getUsername()%></span>
                                        <span class="listingRight">
                                            Pending
                                        </span>
                                    </li>
                              <%}
                            }%>
                        </ul>
                        <%}%>
                   <%} else if (request.getParameter("view").equals("settings")) {%>
                        <div id="midUnderlay" class="panel"></div>
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
                        <br/>
                        <button onclick="clearlStore()">Clear Local Data</button>
                        <br/>
                        <strong>NOTE:</strong> The following settings are LOCAL to your machine, you will need to <em><u>export</u></em> these settings to use them on another machine.
                        <br/><br/>
                        <button>Import Settings</button>&#160;<button>Export Settings</button><br/><br/>
                        <label>Screensaver</label><input type="checkbox"/><br/>
                    <%} else if (request.getParameter("view").equals("admin")) {
                            if (currentUser.isIsAdmin()) {%>
                                <div id="midUnderlayOmni" class="panel"></div>
                                <div id="omniBar" class="panel">
                                    sWave System Server
                                    &#160;&#160;|&#160;&#160;
                                    CPUs: <%=sWave.Server.CPUs%>
                                    &#160;&#160;|&#160;&#160;
                                    JVM Heap: <%=sWave.Server.JVMHEAP%>
                                </div>
                                <h1>Admin Panel</h1>
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
        <%=sWave.UI.footer%>
        <div id="notifier" class="panel"></div>
        <div id="wallpaper"></div>
	<div id="chat">
		<span id="chatHeader" onclick="showHideChat()">
			<select onchange="fetchMessages(this.value)">
                            <%for (Friend friendo : friends) {%>
                                <option value="<%=friendo.getFriendId()%>"><%=udao.getUserById(friendo.getFriendId()).getUsername()%></option>
                            <%}%>
                        </select>
		</span>
		<div id="chatMessages">
			<ul id="theMessages">
                            
			</ul>
		</div>
		<span id="chatFooter">
			<input type="text" id="chatTextBox" placeholder="Type to Chat"/>
		</span>
	</div>
    </body>
</html>
