package Command;

import Daos.LockDao;
import Daos.SongDao;
import Dtos.Lock;
import Dtos.Song;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brian Millar
 */
public class StreamCommand implements Command {

    private static final boolean DEBUG = Debugging.Debug.debug;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        String filename = request.getParameter("songid") + ".mp3";
        File testfile   = new File(filename);
        LockDao locks   = new LockDao();
        locks.releaseUserLocks(Integer.parseInt(request.getParameter("userid")));
        locks.addLock(new Lock(Integer.parseInt(request.getParameter("userid")),
                               Integer.parseInt(request.getParameter("songid"))));
        if (!testfile.exists()) {
            SongDao songs = new SongDao();
            byte songdata[] = (((Song)songs.getSongById(Integer.parseInt(request.getParameter("songid")))).getSongdata());
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
        return "/" + request.getParameter("page") + ".jsp?filename=" + filename + "&playid=" + request.getParameter("songid");
    }
}