package Command;

import Daos.SongDao;
import Dtos.Song;
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
        HttpSession session = request.getSession();

        if (sWave.Server.SERVER_SOCKET == null)
            sWave.Server.createSocket();

        SongDao songs = new SongDao();
        Song  theSong = (Song)songs.getSongById(Integer.parseInt(request.getParameter("songid")));
        byte songdata[] = ((theSong).getSongdata());

        return "/index.jsp";
    }
}