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
            OutputStream out = response.getOutputStream();
            UsersDao dao = new UsersDao();
            byte picture[] = dao.getUserPicture(Integer.parseInt(request.getParameter("userid")));
            if (picture != null) {
                out.write(picture);
                out.flush();
            }
            out.close();
            return null; //We don't want to redirect
        } catch (IOException ex) {
            if (DEBUG)
                ex.printStackTrace();
        }
        return null;
    }
}
