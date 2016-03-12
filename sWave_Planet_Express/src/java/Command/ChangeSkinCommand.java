package Command;

import Daos.UsersDao;
import Dtos.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Brian Millar
 */
public class ChangeSkinCommand implements Command {

    private static final boolean DEBUG = sWave.Server.debugging;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UsersDao users = new UsersDao();
        User current   = (User)session.getAttribute("user");
        String newSkin = request.getParameter("skin").toLowerCase();

        /*Update User object in the session with the new skin to change
          without forcing the user to log out and log back in again.*/
        users.changeSkin(newSkin, current.getUserId());
        current.setSkin(newSkin);
        session.setAttribute("user", current);

        return "/account.jsp?view=settings";
    }
}