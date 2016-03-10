<%-- 
    Document   : receipt.jsp
    Created on : Mar 10, 2016, 12:49:01 AM
    Author     : Brian Millar
--%>

<%@page import="Dtos.Song"%>
<%@page import="Daos.SongDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Dtos.CartItem"%>
<%@page import="Dtos.User"%>
<%@page import="Dtos.UltimateOrder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thank You</title>
    </head>
    <body>
        <% if (request.getParameter("downloading") != null && request.getParameter("downloading").equals("yes")) {%>
        <!-- There is no error here netbeans just doesn't like new goodness like the download attribute -->
        <a id="downloader" download="<%=((Song)session.getAttribute("currentSong")).getTitle() + ".mp3"%>" href="<%=sWave.Server.domain + ((Song)session.getAttribute("currentSong")).getSongId() + ".mp3"%>"></a>
        <script>document.getElementById("downloader").click();</script>
        <%}%>
        <%
        User currentUser = null;
        if (session.getAttribute("user") != null) {
            currentUser = ((User)session.getAttribute("user"));
        } else {
            response.sendRedirect("login.jsp");
        }
        if (session.getAttribute("wasActuallyBought") != null && (boolean)session.getAttribute("wasActuallyBought")) {
            if (session.getAttribute("theUltimateOrder") != null) {
                UltimateOrder theUltimateOrder = (UltimateOrder)session.getAttribute("theUltimateOrder");%>
                <h1>Thank You <%=currentUser.getFname()%></h1>
                <hr/>
                <h5>You bought <%=theUltimateOrder.getMerchSize()%> Unique Merch items and <%=theUltimateOrder.getSongSize()%> Unique Songs worth a total of &euro;<%=theUltimateOrder.getTotal()%></h5>
                <h5>Please find below your receipt</h5>
                <hr/>
                <h5>User: <%=currentUser.getUsername()%></h5>
                <h5>Full Name: <%=currentUser.getFname() + " " + currentUser.getLname()%></h5>
                <h5>Date: <%=theUltimateOrder.getDateOrdered()%></h5>
                <h5>Total: <%=theUltimateOrder.getTotal()%></h5>
                <hr/>
                <%
                SongDao dao = new SongDao();
                for (CartItem c : (ArrayList<CartItem>)session.getAttribute("cart")) {
                    if (c.getType()) {
                        Song s = dao.getSongById(c.getProdId());
                    
                %>
                    <form action="UserActionServlet" method="POST">
                        <input type="hidden" name="action" value="stream"/>
                        <input type="hidden" name="download" value="yes"/>
                        <input type="hidden" name="songid" value="<%=s.getSongId()%>"/>
                        <input type="submit" value="Download <%=s.getTitle()%>"/>
                    </form>
                <%}}%>
                <hr/>
                <a href="index.jsp">Go Home</a>
                <form action="UserActionServlet" method="POST">
                    <input type="hidden" value="logout" name="action"/>
                    <input type="submit" value="Log Out"/>
                </form>
                <hr/>
            <%}
        %>
            
        
        <%}%>
    </body>
</html>
