<%-- 
    Document   : viewCart
    Created on : Mar 9, 2016, 11:06:23 PM
    Author     : Brian Millar
--%>

<%@page import="Dtos.Merch"%>
<%@page import="Dtos.Song"%>
<%@page import="Daos.MerchDao"%>
<%@page import="Daos.SongDao"%>
<%@page import="Dtos.CartItem"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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
                        <%}%>
                        <span>Price: <%=c.getPrice()%></span>
                    </li>
            <%}
            }
        %>
        </ul>
    </body>
</html>
