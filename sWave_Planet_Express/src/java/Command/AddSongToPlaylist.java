package Command;

import Daos.PlaylistDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brian Millar
 */
public class AddSongToPlaylist implements Command {
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        PlaylistDao playDao = new PlaylistDao();
        int s = Integer.parseInt(request.getParameter("songId"));
        int p = Integer.parseInt(request.getParameter("playlistId"));
        playDao.addSongToPlaylist(s, p);
        return null;
    }
}
