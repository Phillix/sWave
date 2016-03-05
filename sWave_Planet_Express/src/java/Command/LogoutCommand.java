package Command;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Austin
 * @author Brian Millar
 */
public class LogoutCommand implements Command {

    private static final boolean DEBUG = Debugging.Debug.debug;
    
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        //If a file is on the server for streaming to this user delete it
        try {
            File current = new File("../webapps/ROOT/" + session.getId() + ".mp3");
            current.delete();
        } catch (Exception e) {
            //The file probably didn't exist
            if (DEBUG)
                e.printStackTrace();
        }
        finally {
            session.invalidate();
            return "/index.jsp";
        }
    }
}
