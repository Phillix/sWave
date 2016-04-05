package Command;

import Daos.SongDao;
import Dtos.Song;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Brian Millar
 */
public class LoadArtworkCommand implements Command {

    private static final boolean DEBUG = sWave.Server.DEBUGGING;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        try {
            SongDao dao = new SongDao();
            Song s = dao.getSongById(Integer.parseInt(request.getParameter("songid")));
            OutputStream out = response.getOutputStream();
            if (s.getArtwork() != null) {
                out.write(s.getArtwork());
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
