/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Daos.FriendDao;
import Dtos.Friend;
import Dtos.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Phillix
 */
public class RequestFriendCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        FriendDao fd = new FriendDao();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        String friendId = request.getParameter("friendId");
        
        if(u != null && friendId != null && !friendId.isEmpty()) {
            Friend f = new Friend(u.getUserId(), Integer.valueOf(friendId));
            fd.requestFriend(f);
            return "/account.jsp";
        }
        return "/error.jsp";
    }
    
}
