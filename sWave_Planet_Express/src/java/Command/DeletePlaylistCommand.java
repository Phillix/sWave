package Command;

import Daos.PlayTracksDao;
import Daos.PlaylistDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Phillix
 */
public class DeletePlaylistCommand implements Command {
    
    private static final boolean DEBUG = sWave.Server.DEBUGGING;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        PlayTracksDao ptDao = new PlayTracksDao();
        PlaylistDao playlistDao = new PlaylistDao();

        try {
            int playlistId = Integer.valueOf(request.getParameter("playlistId"));
            int result = ptDao.deletePlayTracksInPlaylist(playlistId);
            if (result == 0) {
                playlistDao.deletePlaylist(playlistId);
                return "/playlists.jsp";
            }
        } catch (Exception e) {
            if (DEBUG)
                e.printStackTrace();
        }
        return "/error.jsp";
    }
}
