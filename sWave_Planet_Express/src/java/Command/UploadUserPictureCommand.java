package Command;

import Daos.UsersDao;
import Dtos.User;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Brian Millar
 */
public class UploadUserPictureCommand implements Command {

    private static final boolean DEBUG = sWave.Server.DEBUGGING;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            Part picture = (Part)request.getParts().toArray()[1];
            if (picture != null) {
                InputStream fileStream;
                fileStream = picture.getInputStream();
                byte buffer[] = new byte[(int)picture.getSize()];
                int data = fileStream.read();
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = ((byte)data);
                    data = fileStream.read();
                }
                UsersDao dao = new UsersDao();
                if ((User)session.getAttribute("user") != null)
                    dao.addUserPicture(((User)session.getAttribute("user")).getUserId(), buffer);
                else return "/login.jsp";
            }
        } catch (IOException | ServletException ex) {
            if (DEBUG)
                ex.printStackTrace();
        }
        return null; //We don't want to redirect
    }
}
