package Command;

import Daos.SongDao;
import Dtos.Song;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *            session
 * @author Brian Millar
 */
public class StreamNGCommand implements Command {

    private static final boolean DEBUG = sWave.Server.DEBUGGING;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            SongDao dao = new SongDao();
            Song s = dao.getSongById(Integer.parseInt(request.getParameter("songid")));
            OutputStream out = response.getOutputStream();
            out.write(s.getSongdata());
            System.out.println("FOUND: " + s.getSongdata().length);
            out.flush();
            out.close();
            session.setAttribute("currentSongId", Integer.parseInt(request.getParameter("songid")));
            return null; //We don't want to redirect
        } catch (IOException ex) {
            if (DEBUG)
                ex.printStackTrace();
        }
        return null;
    }
    
    /*
        Create a hash of the mp3 data and store it in database.
        Use a hashmap to store songdata on the heap.
        When starting new stream get song hash and see if its in the hashmap
        before reading from DB.
    */
    
}
