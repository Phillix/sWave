<%-- 
    Document   : Messages
    Created on : 27-Apr-2016, 23:03:31
    Author     : Phillix
--%>

<%@page import="Daos.UsersDao"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="Dtos.Friend"%>
<%@page import="Dtos.Message"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Daos.FriendDao"%>
<%@page import="Daos.MessageDao"%>
<%@page import="java.util.Locale"%>
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
                response.sendRedirect("login.jsp?refer=account.jsp&view=" + request.getParameter("view"));
            else {
                currentUser   = (User)session.getAttribute("user");
                skin          = currentUser.getSkin();
                currentLocale = new Locale(currentUser.getLangPref());
            }

            final boolean DEBUG = sWave.Server.DEBUGGING;
            
            MessageDao mdao = new MessageDao();
            UsersDao udao = new UsersDao();
            FriendDao  fdao = new FriendDao();
            ArrayList<Friend> friends = fdao.getUserFriends(currentUser.getUserId());
            ArrayList<Message> conversation = null;
            ResourceBundle messages = ResourceBundle.getBundle("i18n.content", currentLocale);
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%=messages.getString("messagesVar")%></h1>
        <% 
            for(Friend f : friends) {
                
              if(f.getUserId() != currentUser.getUserId())
                  conversation = mdao.getConversation(f.getUserId());
              
              else
                 conversation = mdao.getConversation(f.getFriendId());
              for(Message m : conversation) {
                  
                  if(m.getSender() != currentUser.getUserId()) {
                      User u = udao.getUserById(m.getSender());
           %> 
        <ul>
            <li>
                <h3><%=messages.getString("sentFromVar")%> <%=u.getUsername()%><span><u><%=m.getDate()%></u></span></h3>
                <br/>
                <span>
                    <%=m.getContent()%>
                </span>
                <br/><br/>
            </li>
        </ul>
        <%
                  }
                  else {
                      User u = udao.getUserById(m.getReceiver());
         %>
         <ul>
            <li>
                <h3><%=messages.getString("sentToVar")%> <%=u.getUsername()%><span><u><%=m.getDate()%></u></span></h3>
                <br/>
                <span>
                    <%=m.getContent()%>
                </span>
                <br/><br/>
            </li>
        </ul>
          <%        }
              }
            }  
        %>        
    </body>
</html>
