package Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Austin
 */
public class LogoutCommand implements Command {

    private static final boolean DEBUG = sWave.Server.DEBUGGING;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate(); //Invalidate the session
        return "/login.jsp";
    }
}
