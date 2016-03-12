package Command;

import Daos.LockDao;
import Daos.SongDao;
import Dtos.Lock;
import Dtos.Song;
import Dtos.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sWave.SongLocking;

/**
 *            session
 * @author Brian Millar
 */
public class StreamCommand implements Command {

    private static final boolean DEBUG = sWave.Server.DEBUGGING;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        String filename     = request.getParameter("songid") + ".mp3";
        File testfile       = new File(filename);
        LockDao locks       = new LockDao();
        HttpSession session = request.getSession();
        locks.releaseUserLocks(((User)session.getAttribute("user")).getUserId());
        locks.addLock(new Lock(((User)session.getAttribute("user")).getUserId(),
                               Integer.parseInt(request.getParameter("songid"))));
        try {
            SongLocking.clean();
        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
        }
        if (!testfile.exists()) {
            SongDao songs = new SongDao();
            Song  theSong = (Song)songs.getSongById(Integer.parseInt(request.getParameter("songid")));
            byte songdata[] = ((theSong).getSongdata());
            theSong.setSongdata(null);
            request.getSession().setAttribute("currentSong", theSong);
            FileOutputStream output = null;
            try {
                output = new FileOutputStream(new File("../webapps/ROOT/" + filename));
                output.write(songdata);
            } catch (IOException ex) {
                if (DEBUG)
                    ex.printStackTrace();
                return "/error.jsp";
            } finally {
                try {
                    output.close();
                } catch (IOException ex) {
                    if (DEBUG)
                        ex.printStackTrace();
                }
            }
        }
        if (request.getParameter("download") != null && request.getParameter("download").equals("yes"))
            return "/receipt.jsp?downloading=yes";
        return "/index.jsp";
    }
}