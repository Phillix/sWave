package Command;

import Daos.UsersDao;
import Dtos.User;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Brian Millar
 */
public class LoadUserPictureCommand implements Command {

    private static final boolean DEBUG = sWave.Server.DEBUGGING;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            if ((User)session.getAttribute("user") != null) {
                User u = (User)session.getAttribute("user");
                OutputStream out = response.getOutputStream();
                UsersDao dao = new UsersDao();
                System.out.println(dao.getUserPicture(u.getUserId()).length);
                out.write(dao.getUserPicture(u.getUserId()));
                out.flush();
                out.close();
                return null; //We don't want to redirect
            } else return "/login.jsp";
        } catch (IOException ex) {
            if (DEBUG)
                ex.printStackTrace();
        }
        return null;
    }
}
