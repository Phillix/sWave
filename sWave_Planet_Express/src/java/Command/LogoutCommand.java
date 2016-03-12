package Command;

import Daos.LockDao;
import Dtos.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Austin
 * @author Brian Millar
 */
public class LogoutCommand implements Command {

    private static final boolean DEBUG = sWave.Server.debugging;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //Remove any locks a user has on a song before logging them out
        LockDao locks = new LockDao();
        locks.releaseUserLocks(((User)session.getAttribute("user")).getUserId());
        session.invalidate(); //Invalidate the session
        return "/login.jsp";
    }
}
