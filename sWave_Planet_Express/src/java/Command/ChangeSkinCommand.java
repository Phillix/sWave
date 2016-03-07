package Command;

import Daos.UsersDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brian Millar
 */
public class ChangeSkinCommand implements Command {

    private static final boolean DEBUG = Debugging.Debug.debug;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        UsersDao users = new UsersDao();
        users.changeSkin(request.getParameter("skin"));
        return "/account.jsp?view=settings";
    }
}