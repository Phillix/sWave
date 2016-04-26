package Command;

import Daos.FriendDao;
import Dtos.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Phillix
 */
public class ConfirmFriendCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        FriendDao fd = new FriendDao();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        String friendId = request.getParameter("friendId");
        
        if(u != null && friendId != null && !friendId.isEmpty()) {
            fd.confirmFriend(u.getUserId(), Integer.valueOf(friendId));
            return "/account.jsp?view=friends";
        }
        return "/error.jsp";
    }
    
}
