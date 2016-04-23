package Command;

import Daos.SongDao;
import Dtos.Song;
import java.io.IOException;
import java.io.Writer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *            session
 * @author Brian Millar
 */
public class SongInfoDisplayCommand implements Command {

    private static final boolean DEBUG = sWave.Server.DEBUGGING;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        try {
            SongDao dao = new SongDao();
            Song s = dao.getSongById(Integer.parseInt(request.getParameter("songid")));
            Writer out = response.getWriter();
            out.write(String.format("%s :  %s", s.getArtist(), s.getTitle()));
            out.flush();
            out.close();
            return null; //We don't want to redirect
        } catch (IOException ex) {
            if (DEBUG)
                ex.printStackTrace();
        }
        return null;
    }
}
